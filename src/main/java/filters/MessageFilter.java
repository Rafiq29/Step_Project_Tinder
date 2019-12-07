package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            int i = Integer.parseInt(request.getParameter("id"));
            chain.doFilter(request, response);
        } catch (NumberFormatException ex) {
            if (response instanceof HttpServletResponse)
                ((HttpServletResponse) response).sendRedirect("/login/");
            else
                ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
