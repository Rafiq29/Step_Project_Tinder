package servlet;

import libs.User;
import service.RegisterService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RegisterServlet extends HttpServlet {

    private RegisterService registerService;

    public RegisterServlet(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Path path = Paths.get("/content/register.html");
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path, outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        registerService.register(new User(
                req.getParameter("email"),
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("password"),
                req.getParameter("gender"),
                req.getParameter("profession"),
                ""));
        resp.sendRedirect("/login/");
    }
}
