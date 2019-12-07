package filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EmptyCookieFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (cookies != null)
                chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
