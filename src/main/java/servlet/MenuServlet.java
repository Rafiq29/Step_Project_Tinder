package servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie oneCookie : cookies) {
            if (oneCookie.getName().equals("%USERLIKE%") || oneCookie.getName().equals("%ID%")) {
                oneCookie.setMaxAge(0);
                resp.addCookie(oneCookie);
            }
        }
        Path path = Paths.get("content/menu.html");
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        Files.copy(path, servletOutputStream);
    }
}
