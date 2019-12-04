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
        //TODO: get image url and username for like
        data.put("imgURL","");
        data.put("username","");

        engine.render("like-page.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        LikedService likedService = new LikedService();
        //TODO likedService.like("user_Like","user_liked")
        resp.sendRedirect("/users/*");
    }
}
