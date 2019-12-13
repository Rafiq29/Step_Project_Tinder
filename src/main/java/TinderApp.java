import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.AddCssServlet;
import service.LikedService;
import servlet.*;

public class TinderApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8088);
        ServletContextHandler handler = new ServletContextHandler();

        LikedService likedService = new LikedService();
        handler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        handler.addServlet(new ServletHolder(new AddCssServlet()), "/static/*");
        handler.addServlet(new ServletHolder(new LikeServlet()), "/like/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        handler.addServlet(new ServletHolder(new MessagesServlet()), "/messages/*");
        handler.addServlet(new ServletHolder(new LikedServlet(likedService)), "/liked/*");
        handler.addServlet(new ServletHolder(new MenuServlet()), "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
