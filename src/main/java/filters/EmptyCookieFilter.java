package filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCookieFilter implements Filter {
    private final String path;

    public EmptyCookieFilter(String path) {
        this.path = path;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (cookies != null)
                chain.doFilter(request, response);
            else {
                Cookie cookie = new Cookie("%TEST%", "-1");
                ((HttpServletResponse) response).addCookie(cookie);
                ((HttpServletResponse) response).sendRedirect(path);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
