package servlet;

import libs.TemplateEngine;
import libs.User;
import service.ManuallyAddCss;
import service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        addCss.addCssBoot();
        addCss.addCssStyle();
        HashMap<String, Object> data = addCss.get();
        engine.render("register.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterService registerService = new RegisterService();
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
