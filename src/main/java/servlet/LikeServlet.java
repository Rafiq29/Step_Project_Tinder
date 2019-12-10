package servlet;

import libs.TemplateEngine;
import libs.User;
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
    private User user;

    public LikeServlet() {
        service = new LikeService();
        user = service.getFirst();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies)
            if (oneCookie.getName().equals("%ID%"))
                service.setLocalId(Integer.parseInt(oneCookie.getValue()));
        if (user.getId() == service.getLocalId()) {
            user = service.getNext();
        }
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        addCss.addCssStyle();
        addCss.addCssFont();
        addCss.addCssBoot();
        HashMap<String, Object> data = addCss.get();
        data.put("id", user.getId());
        data.put("name", user.getName());
        data.put("surname", user.getSurname());
        data.put("username", user.getUsername());
        data.put("imgURL", user.getImgURL());
        engine.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            user = service.getNext();
            String like = req.getParameter("like");
            if (like != null)
                service.like(user.getId());
            resp.sendRedirect("/like/");
        } catch (IndexOutOfBoundsException ex) {
            resp.sendRedirect("/liked/");
        }
    }
}
