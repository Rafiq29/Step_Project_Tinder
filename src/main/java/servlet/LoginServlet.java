package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get("content/login.html");
        ServletOutputStream servletOutputStream =resp.getOutputStream();
        Files.copy(path,servletOutputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String login = req.getParameter("email_adress");
        String password = req.getParameter("Password");
        try {
            writer.println("<p>Email_Adress: " + login + "</p>");
            writer.println("<p>password: " + password + "</p>");
        } finally {
            writer.close();
        }
    }
        //Save(login,password);

}
