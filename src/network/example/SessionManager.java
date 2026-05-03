package network.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private final ConcurrentHashMap<String, Socket> sessionHolder = new ConcurrentHashMap<>();

    public void broadcast(String message) {
        sessionHolder.values().iterator().forEachRemaining((Socket v) -> {
            try {
                DataOutputStream output = new DataOutputStream(v.getOutputStream());
                output.writeUTF(message);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean add(String key, Socket socket) {
        Socket saved = sessionHolder.putIfAbsent(key, socket);
        return socket.equals(saved);
    }

    public void remove(String key) {
        sessionHolder.remove(key);
    }

    public void change(String newKey, String oldKey, Socket socket) {
        boolean save = this.add(newKey, socket);
        if (save) sessionHolder.remove(oldKey);
    }

    public void sendMessage(Socket socket, String message) {

    }

    public String getMessage(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
        DataInputStream dis = new DataInputStream(input);

        return dis.readUTF();
    }

    public Set<String> getMemberList() {
        return sessionHolder.keySet();
    }
}
