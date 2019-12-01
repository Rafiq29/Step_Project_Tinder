import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;

public class TinderApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8088);

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new UserServlet()),"/users/*");
        handler.addServlet(new ServletHolder(new LoginServlet()),"/login/*");
        handler.addServlet(new ServletHolder(new MessagesServlet()),"/messages/*");
        handler.addServlet(new ServletHolder(new LikedServlet()),"/liked");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
