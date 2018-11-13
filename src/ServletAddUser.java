import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "ServletAddUser", urlPatterns = "/adduser")
public class ServletAddUser extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SalvarContato</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SalvarContato at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");

        try{
            Connection mysql = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "QWE123");

            String instrucao = "INSER INTO contato(nome, endereco) VALUES (?, ?)";

            PreparedStatement pst = mysql.prepareStatement(instrucao);

            pst.setString(1, nome);
            pst.setString(2, endereco);

            pst.execute();
        }
        catch(SQLException e){
            out.println("Erro de conexao");
            out.println(e.getMessage());
        }
    }
}

