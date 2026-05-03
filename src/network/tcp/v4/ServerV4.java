package network.tcp.v4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV4 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("server start");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("server socket start - port: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            log("socket connect: " + socket);

            SessionV4 session = new SessionV4(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
