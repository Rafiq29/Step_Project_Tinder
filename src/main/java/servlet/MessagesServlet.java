package servlet;

import libs.TemplateEngine;
import service.MessageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class MessagesServlet extends HttpServlet {
    private int senderId;
    private int receiverId;
    private MessageService service;

    public MessagesServlet() {
        senderId = -1;
        receiverId = -1;
        service = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies)
            if (oneCookie.getName().equals("%ID%"))
                senderId = Integer.parseInt(oneCookie.getValue());
        receiverId = Integer.parseInt(req.getParameter("id"));
        TemplateEngine engine = new TemplateEngine("./content");
        HashMap<String, Object> data = new HashMap<>();
        data.put("messages", service.getMessages(senderId, receiverId));
        engine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String message = req.getParameter("message");
        service.write(senderId, receiverId, message);
        //TODO  messageWriter.writeMessage("message","Date","ID","User_TO","User_FROM");
    }
}
