package servlet;

import libs.TemplateEngine;
import libs.User;
import service.MessageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


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
        User user = service.getUser(receiverId);
        List<String> formattedMessages = service.getFormattedMessages(senderId, receiverId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("userTo", user.getName());
        if (!formattedMessages.isEmpty())
            data.put("messages", formattedMessages);
        else
            data.put("messages", new LinkedList<Integer>());


        engine.render("chat.ftl", data, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = req.getParameter("message");
        service.write(senderId, receiverId, message);
        //TODO  messageWriter.writeMessage("message","Date","ID","User_TO","User_FROM");
    }
}
