package servlet;
import libs.LoginException;
import libs.TemplateEngine;
import libs.User;
import service.LoginService;
import service.ManuallyAddCss;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        HashMap<String, Object> data = addCss.addCss(true, true, false);
        engine.render("login.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("pass");
        // TODO Farid's note : replace "./content/like-page.html" to servlet path html redirect doesn't work use servlet path
        try {
            LoginService loginService = new LoginService();
            loginService.check(new User(login,password));
            resp.sendRedirect("./content/like-page.html");
        }
        catch (LoginException ex) {
            resp.sendRedirect("./content/login.ftl");
        }
    }
}
