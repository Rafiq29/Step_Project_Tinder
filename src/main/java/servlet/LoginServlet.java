package servlet;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get("./content/login.html");
        ServletOutputStream servletOutputStream =resp.getOutputStream();
        Files.copy(path,servletOutputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("pass");
        try {
            LoginService loginService = new LoginService();
            loginService.check(login,password);
            resp.sendRedirect("./content/like-page.html");
        }
        catch (Exception ex) {
            resp.sendRedirect("./content/login.html");
        }
    }
}
