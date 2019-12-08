package servlet;

import libs.TemplateEngine;
import libs.User;
import service.LikeService;
import service.ManuallyAddCss;

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
        TemplateEngine engine = new TemplateEngine("./content");
        ManuallyAddCss addCss = new ManuallyAddCss();
        HashMap<String, Object> data = addCss.addCss(true, true, true);
        data.put("id", user.getId());
        data.put("name", user.getName());
        data.put("surname", user.getSurname());
        data.put("username", user.getUsername());
        data.put("imgURL", user.getImgURL());
        engine.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        user = service.getNext(user.getId());
        service.setLocalId(user.getId());
        String likeId = req.getParameter("like");
        int id = -1;
        if (likeId != null)
            id = Integer.parseInt(likeId);
        if (id != -1)
            service.like(id);
        if (service.isLast(id) && LikeService.liked)
            resp.sendRedirect("/liked/");
        else
            resp.sendRedirect("/like/");
        //TODO likedService.like("user_liked")
    }
}
