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
    public boolean isFirstTime = true;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (isFirstTime) {
            Cookie[] cookies = req.getCookies();
            if (cookies!=null) {
                for (Cookie oneCookie : cookies) {
                    if (oneCookie.getName().equals("%ID%")) {
                        oneCookie.setMaxAge(0);
                        resp.addCookie(oneCookie);
                    }
                }
            }
            isFirstTime = false;
        }
        Path path = Paths.get("content/menu.html");
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        Files.copy(path, servletOutputStream);
    }
}
