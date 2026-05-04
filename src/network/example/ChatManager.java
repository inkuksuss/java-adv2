package network.example;

import java.net.Socket;
import java.util.List;
import java.util.UUID;

import static util.MyLogger.log;

public class ChatManager {

    private final static String JOIN = "join";
    private final static String MESSAGE = "message";
    private final static String CHANGE_NAME = "changeName";
    private final static String MEMBER_LIST = "memberList";
    private final static String EXIT = "exit";

    private final SessionManager sessionManager;

    public ChatManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void join(Socket socket, Message message) {
        try {
            String key = UUID.randomUUID().toString().substring(0, 8);
            String newName = message.getMessage();
            Session session = new Session(newName, socket);
            boolean saved = sessionManager.add(key, session);
            System.out.println(saved);

            if (!saved) {
                log("login failed: " + message);
                Message failedMessage = new Message();
                failedMessage.setId(null);
                sessionManager.sendMessage(session, failedMessage);

                return;
            }

            Message res = new Message();
            res.setId(key);
            res.setType("join");
            res.setMessage(newName + "님이 입장하셨습니다.");
            sessionManager.broadcast(res);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive(Socket socket) {
        try {
            while (true) {
                Message message = sessionManager.getMessage(socket);
                log("Receive: " + message.toString());

                if (EXIT.equals(message.getType())) {
                    this.exit(message);
                }
                else if (JOIN.equals(message.getType())) {
                    this.join(socket, message);
                }
                else if (CHANGE_NAME.equals(message.getType())) {
                    this.changeName(socket, message);
                }
                else if (MEMBER_LIST.equals(message.getType())) {
                    this.sendMemberList();
                }
                else {
                    this.sendMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Message message) {
        Message rsp = new Message();
        rsp.setType(message.getType());
        rsp.setMessage(message.getMessage());

        sessionManager.broadcast(rsp);
    }

    private void changeName(Socket socket, Message message) {
        Session session = new Session(message.getMessage(), socket);
        sessionManager.updateSession(message.getId(), session);
    }

    private void sendMemberList() {
        sessionManager.getMemberList();
    }

    private void exit(Message message) {
        sessionManager.remove(message.getId());

        Message rsp = new Message();
        rsp.setType(EXIT);
        rsp.setMessage(message.getMessage() + "님이 퇴장하셨습니다.");
        sessionManager.broadcast(rsp, List.of(message.getId()));
    }

    public void close() {
        sessionManager.close();
    }
}
