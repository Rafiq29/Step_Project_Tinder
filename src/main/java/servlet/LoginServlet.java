package servlet;

import libs.LoginException;
import libs.TemplateEngine;
import libs.User;
import service.LoginService;
import service.ManuallyAddCss;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        HashMap<String, Object> data = addCss.addCss(true, true, false);
        engine.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            LoginService loginService = new LoginService();
            int id = loginService.check(new User(login, password));
            resp.addCookie(new Cookie("%ID%", String.valueOf(id)));
            resp.sendRedirect("/like/*");
        } catch (LoginException ex) {
            resp.sendRedirect("/login/*");
        }
    }
}
