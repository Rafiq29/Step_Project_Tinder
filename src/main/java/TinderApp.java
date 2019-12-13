import filters.EmptyCookieFilter;
import filters.MessageFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TinderApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8088);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addFilter(new FilterHolder(new EmptyCookieFilter("/like/*")), "/like/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new EmptyCookieFilter("/messages/*")), "/messages/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new MessageFilter()), "/messages/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new EmptyCookieFilter("/login/*")), "/login/*", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        handler.addServlet(new ServletHolder(new LikeServlet()), "/like/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        handler.addServlet(new ServletHolder(new MessagesServlet()), "/messages/*");
        handler.addServlet(new ServletHolder(new LikedServlet()), "/liked/*");
        handler.addServlet(new ServletHolder(new MenuServlet()), "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
