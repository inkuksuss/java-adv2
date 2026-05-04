package was.httpserver.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

public class NotFoundServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>404</h1>");
    }
}
