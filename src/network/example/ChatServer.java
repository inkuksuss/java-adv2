package network.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ChatServer {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        ChatManager chatManager = new ChatManager(new SessionManager());
        Runtime.getRuntime()
                .addShutdownHook(new Thread(new ShutdownHook(serverSocket, chatManager)));

        while (true) {
            //join
            Socket socket = serverSocket.accept();
            log("socket connect: " + socket);
            chatManager.receive(socket);
        }

    }

    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final ChatManager chatManager;

        public ShutdownHook(ServerSocket serverSocket, ChatManager chatManager) {
            this.serverSocket = serverSocket;
            this.chatManager = chatManager;
        }

        @Override
        public void run() {
            log("call shut down");
            try {
                chatManager.close();
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
