package servlet;

import service.MessageWriter;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Cookie[] cookies = req.getCookies();
            int senderID = -1;
            int reciever = -1;
            if (cookies != null)
                for (Cookie oneCookie : cookies)
                    if (oneCookie.getName().equals("%ID%"))
                        senderID = Integer.parseInt(oneCookie.getValue());
            reciever = Integer.parseInt(req.getParameter("id"));
            Path path = Paths.get("./content/chat.ftl");
            ServletOutputStream servletOutputStream = resp.getOutputStream();
            Files.copy(path, servletOutputStream);
        } catch (NumberFormatException ex) {
            resp.sendRedirect("/login/");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MessageWriter messageWriter = new MessageWriter();
        //TODO  messageWriter.writeMessage("message","Date","ID","User_TO","User_FROM");
    }
}
