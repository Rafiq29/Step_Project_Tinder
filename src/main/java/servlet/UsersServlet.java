package servlet;

import libs.TemplateEngine;
import service.LikedService;
import service.ManuallyAddCss;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
    HashMap<String,String> profiles = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        HashMap<String, Object> data = addCss.addCss(true, true, true);
        engine.render("like-page.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        LikedService likedService = new LikedService();
        //likedService.like() TODO:Add POST request handler on the server and store the user's choice (yes or no) on the server (in any form)
        resp.sendRedirect("./content/like-page.ftl");
    }
}
