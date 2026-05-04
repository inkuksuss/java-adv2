package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process(socket);
        } catch (IOException e) {
            log(e);
        }
    }

    private void process(Socket socket) throws IOException {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {
            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                log("favicon request");
                return;
            }
            log("HTTP request info");
            System.out.println(requestString);

            log("HTTP response create...");

            if (requestString.startsWith("GET /site1")) {
                site1(printWriter);
            } else if (requestString.startsWith("GET /site2")) {
                site2(printWriter);
            } else if (requestString.startsWith("GET /search")) {
                search(printWriter, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(printWriter);
            } else {
                notFound(printWriter);
            }
            printWriter.flush();
            log("response complete");
        }
    }

    private void site1(PrintWriter printWriter) {
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html; charset=UTF-8");
        printWriter.println();
        printWriter.println("<h1>site 1</h1>");
    }

    private void site2(PrintWriter printWriter) {
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html; charset=UTF-8");
        printWriter.println();
        printWriter.println("<h1>site 2</h1>");
    }

    private void search(PrintWriter printWriter, String requestString) {
        int startIdx = requestString.indexOf("q=");
        int endIdx = requestString.indexOf(" ", startIdx + 2);
        String query = requestString.substring(startIdx + 2, endIdx);
        String decode = URLDecoder.decode(query, UTF_8);

        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html; charset=UTF-8");
        printWriter.println();
        printWriter.println("<h1>search</h1>");
        printWriter.println("<ul>");
        printWriter.println("<li>query: " + query + "</li>");
        printWriter.println("<li>decode: " + decode + "</li>");
        printWriter.println("</ul>");
    }

    private void home(PrintWriter printWriter) {
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html; charset=UTF-8");
        printWriter.println();
        printWriter.println("<h1>home</h1>");
        printWriter.println("<ul>");
        printWriter.println("<li><a href='/site1'>site1</a></li>");
        printWriter.println("<li><a href='/site2'>site2</a></li>");
        printWriter.println("<li><a href='/search?q=hello'>search</a></li>");
        printWriter.println("</ul>");
    }

    private void notFound(PrintWriter printWriter) {
        printWriter.println("HTTP/1.1 404 Not Found");
        printWriter.println("Content-Type: text/html; charset=UTF-8");
        printWriter.println();
        printWriter.println("<h1>404</h1>");
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void responseToClient(PrintWriter printWriter) {
        String body = "<H1>Hello world</H1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n");
        sb.append(body);

        log("HTTP response info");
        System.out.println(sb);

        printWriter.println(sb);
        printWriter.flush();
    }

    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
