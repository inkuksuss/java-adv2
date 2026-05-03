package network.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        serverSocket.setSoTimeout(3000);

        ChatManager chatManager = new ChatManager(new SessionManager());

        while (true) {
            // join
            Socket socket = serverSocket.accept();

            chatManager.join(socket);

            chatManager.receive(socket);
        }

    }

    // join
    // sendMessage
    // changeName
    // getUserList
    // close
}
