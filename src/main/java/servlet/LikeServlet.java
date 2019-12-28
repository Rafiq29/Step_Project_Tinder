package servlet;

import libs.OutOfUserException;
import libs.TemplateEngine;
import libs.User;
import service.LikeService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LikeServlet extends HttpServlet {
    private LikeService service;
    private User user;

    public LikeServlet(LikeService service) {
        this.service = service;
        user = service.getFirst();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies)
            if (oneCookie.getName().equals("%ID%"))
                service.setLocalId(Integer.parseInt(oneCookie.getValue()));
        if (user.getId() == service.getLocalId())
            user = service.getNext(user.getId());

        TemplateEngine engine = new TemplateEngine("./content");
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("name", user.getName());
        data.put("surname", user.getSurname());
        data.put("username", user.getUsername());
        data.put("imgURL", user.getImgURL());
        engine.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String like = req.getParameter("like");
        if (like != null)
            service.like(Integer.parseInt(like));
        try {
            user = service.getNext(user.getId());
            resp.sendRedirect("/like/");
        } catch (OutOfUserException ex) {
            resp.sendRedirect("/liked/");
        }
    }
}
