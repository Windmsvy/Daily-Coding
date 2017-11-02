import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
public class MyHttpServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("I am Http do Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("I am Http do Post");
    }
}
