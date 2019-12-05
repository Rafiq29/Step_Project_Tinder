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
import java.sql.SQLException;
import java.util.HashMap;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        HashMap<String, Object> data = addCss.addCss(true, true, false);
        engine.render("register.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterService registerService = new RegisterService();
        try {
           // String username, String name, String surname, String password, String gender, String profession, String imgURL
            registerService.register(new User(
                    req.getParameter("email"),
                    req.getParameter("name"),
                    req.getParameter("surname"),
                    req.getParameter("password"),
                    req.getParameter("gender"),
                    req.getParameter("profession"),
                    ""));
            resp.sendRedirect("/login/");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
