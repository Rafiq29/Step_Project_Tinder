package servlet;

import libs.TemplateEngine;
import service.LikeService;
import service.ManuallyAddCss;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        HashMap<String, Object> data = addCss.addCss(true, true, true);
        //TODO: get image url and username for like
        Cookie[] cookies = req.getCookies();
        LikeService service = new LikeService();
        Cookie cookie = service.getNext(data,cookies);
        resp.addCookie(cookie);
        engine.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        //TODO likedService.like("user_Like","user_liked")
        resp.sendRedirect("/users/*");
    }
}
