package was.v9;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV3;


import java.io.IOException;
import java.util.List;

public class ServerMainV9 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteControllerV9(), new SearchControllerV9());
        AnnotationServletV3 annotationServletV3 = new AnnotationServletV3(controllers);
        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServletV3);
        servletManager.add("/favicon.ico", new DiscardServlet());

        HttpServer httpServer = new HttpServer(PORT, servletManager);
        httpServer.start();
    }
}
