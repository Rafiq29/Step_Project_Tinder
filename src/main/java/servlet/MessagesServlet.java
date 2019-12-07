package servlet;

import libs.TemplateEngine;
import service.MessageService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MessagesServlet extends HttpServlet {
    private int senderId;
    private int receiverId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        senderId = -1;
        receiverId = -1;
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies)
            if (oneCookie.getName().equals("%ID%"))
                senderId = Integer.parseInt(oneCookie.getValue());
        receiverId = Integer.parseInt(req.getParameter("id"));
        TemplateEngine engine = new TemplateEngine("./content");
        Path path = Paths.get("./content/chat.ftl");
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        Files.copy(path, servletOutputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MessageService service = new MessageService();
        String message = req.getParameter("message");
        service.write(senderId, receiverId, message);

        //TODO  messageWriter.writeMessage("message","Date","ID","User_TO","User_FROM");
    }
}
