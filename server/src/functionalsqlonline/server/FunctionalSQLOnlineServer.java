package functionalsqlonline.server;

import functionalsqlonline.exceptionmappers.CustomExceptionMapper;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class FunctionalSQLOnlineServer {

    private Server server;

    private String address = "127.0.0.1";
    private int port = 9000;

    public void start() throws Exception {
        org.eclipse.jetty.util.thread.QueuedThreadPool threadPool = new org.eclipse.jetty.util.thread.QueuedThreadPool();
        threadPool.setMinThreads(1);
        threadPool.setMaxThreads(10);
        server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost(address);

        server.setConnectors(new Connector[] { connector });

        setupRestContext(server);

        server.start();
        server.setStopAtShutdown(true);
    }

    private void setupRestContext(Server server) {
        WebAppContext context;

        context = new WebAppContext();
        context.setContextPath("");

        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setServletContext(context.getServletContext());
        applicationContext.register(ApplicationContext.class);

        ResourceConfig jerseyResourceConfig = new ResourceConfig();
        jerseyResourceConfig.property("contextConfig", applicationContext);
        jerseyResourceConfig.packages("functionalsqlonline");
        jerseyResourceConfig.register(CustomExceptionMapper.class);

        ServletContainer jerseyContainer = new ServletContainer(jerseyResourceConfig);
        ServletHolder jerseySpringServlet = new ServletHolder("jersey-spring-servlet", jerseyContainer);
        context.addServlet(jerseySpringServlet, "/*");

        context.addEventListener(new RequestContextListener());
        context.addEventListener(new ContextLoaderListener(applicationContext));
        context.setResourceBase(".");

        server.setHandler(context);
    }
}
