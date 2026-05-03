package network.example;

import java.io.IOException;
import java.net.Socket;

public class ChatManager {

    private final SessionManager sessionManager;

    public ChatManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void join(Socket socket) {
        try {
            String name = sessionManager.getMessage(socket);

            boolean saved = sessionManager.add(name, socket);
            if (!saved) {
                sessionManager.sendMessage(socket, "");
                return;
            }

            sessionManager.broadcast("/join " + name);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void receive(Socket socket) {
        try {
            while (true) {
                String message = sessionManager.getMessage(socket);

                if (message.startsWith("/exit")) this.exit(socket);
                else if (message.startsWith("/change")) this.changeName(socket, message);
                else if (message.startsWith("/users")) this.sendMemberList();
                else this.sendMessage(socket, message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(Socket socket, String message) {
        sessionManager.broadcast(message);
    }

    private void changeName(Socket socket, String message) {
        sessionManager.change(message.split("|")[0], message.split("|")[1], socket);
    }

    private void sendMemberList() {
        sessionManager.getMemberList();
    }

    private void exit(Socket socket) {
//        sessionManager.remove(socket);
    }
}
