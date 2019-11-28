import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.Servlet;
import servlet.TestServlet;

public class TinderApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8088);

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new TestServlet()),"/users/*");
        handler.addServlet(new ServletHolder(new Servlet()),"/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}


