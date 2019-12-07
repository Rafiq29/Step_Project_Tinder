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
    private LikeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        HashMap<String, Object> data = addCss.addCss(true, true, true);
        Cookie[] cookies = req.getCookies();
        service = new LikeService(cookies);
        Cookie cookie = service.getNext(data);
        resp.addCookie(cookie);
        engine.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String likeId = req.getParameter("like");
        int id = -1;
        if (likeId != null)
            id = Integer.parseInt(likeId);
        if (id != -1)
            service.like(id);
        boolean last = service.isLast();
        boolean liked = LikeService.liked;
        if (last && liked)
            resp.sendRedirect("/liked/*");
        resp.sendRedirect("/like/");
        //TODO likedService.like("user_liked")
    }
}
