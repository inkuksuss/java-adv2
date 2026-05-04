package was.v5.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

import java.io.IOException;

public class Site1Servlet implements HttpServlet {
    public Site1Servlet() {
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>site 1</h1>");
    }
}
