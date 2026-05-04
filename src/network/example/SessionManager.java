package network.example;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static util.MyLogger.log;

public class SessionManager {

    private final ConcurrentHashMap<String, Session> sessionHolder = new ConcurrentHashMap<>();

    public void broadcast(Message message) {
        sessionHolder.values().iterator().forEachRemaining((Session s) -> {
            this.sendMessage(s, message);
        });
    }

    public void broadcast(Message message, List<String> blackList) {
        sessionHolder.forEach((k, v) -> {
            if (!blackList.contains(k)) this.sendMessage(v, message);
        });
    }

    public boolean add(String key, Session session) {
        Session saved = sessionHolder.putIfAbsent(key, session);

        return saved == null;
    }

    public void remove(String key) {
        sessionHolder.remove(key);
    }

    public void updateSession(String key, Session session) {
        sessionHolder.replace(key, session);
    }

    public void sendMessage(Session session, Message message) {
        try {
            Socket socket = session.getSocket();
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(message);
            log("send = " + message);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Message getMessage(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);
            return (Message) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getMemberList() {
        return sessionHolder
                .values()
                .stream()
                .map(session -> session.getName()).toList();
    }

    public synchronized void close() {
        sessionHolder.values().iterator().forEachRemaining(session -> {
            try {
                session.getSocket().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        sessionHolder.clear();
    }
}
