package was.v5.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

import java.io.IOException;

public class SearchServlet implements HttpServlet {
    public SearchServlet() {
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + request.getParam("q") + "</li>");
        response.writeBody("</ul>");
    }
}
