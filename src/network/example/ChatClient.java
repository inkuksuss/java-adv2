package network.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;

import static util.MyLogger.log;

public class ChatClient {

    private static final int PORT = 12345;
    private final static String JOIN = "join";
    private final static String MESSAGE = "message";
    private final static String CHANGE_NAME = "changeName";
    private final static String MEMBER_LIST = "memberList";
    private final static String EXIT = "exit";

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        ExecutorService es = Executors.newFixedThreadPool(2);

        try {
            socket = new Socket("localhost", PORT);
            log("socket connect: " + socket);
            es.submit(new WriteHandler(socket));
            Scanner scanner = new Scanner(System.in);
            boolean saveName = false;

            do {
                System.out.print("이름을 입력해주세요: ");
                String name = scanner.nextLine();

                Message message = new Message();
                message.setType(JOIN);
                message.setMessage(name);
                log("create msg = " + message);
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(message);
                ois = new ObjectInputStream(socket.getInputStream());

                Message response = (Message) ois.readObject();
                log("resp = " + response.toString());
                if (response.getId() != null) {
                    System.out.printf("닉네임이 저장되었습니다.");
                    saveName = true;
                }
                ois.close();
            } while (!saveName);

            es.submit(new ReadHandler(socket));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            ois.close();
            oos.close();
            socket.close();
        }
    }

    static class WriteHandler implements Callable<Void> {

        private final Socket socket;

        public WriteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws IOException {
            Scanner scanner = new Scanner(System.in);
            boolean saveName = false;

            do {
                System.out.print("이름을 입력해주세요: ");
                String name = scanner.nextLine();

                Message message = new Message();
                message.setType(JOIN);
                message.setMessage(name);
                log("create msg = " + message);
//                oos = new ObjectOutputStream(socket.getOutputStream());
//                oos.writeObject(message);
//                ois = new ObjectInputStream(socket.getInputStream());
//
//                Message response = (Message) ois.readObject();
//                log("resp = " + response.toString());
//                if (response.getId() != null) {
//                    System.out.printf("닉네임이 저장되었습니다.");
//                    saveName = true;
//                }
//                ois.close();
            } while (!saveName);

            while (true) {
                String input = scanner.nextLine();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                Message message = new Message();

                if (input.startsWith("/exit")) {
                    message.setType(EXIT);
                    message.setMessage(input.replace("/exit", ""));
                }
                else if (input.startsWith("/change")) {
                    message.setType(CHANGE_NAME);
                    message.setMessage(input.replace("/change", ""));
                }
                else if (input.startsWith("/users")) {
                    message.setType(MEMBER_LIST);
                }
                else {
                    message.setType(MESSAGE);
                    message.setMessage(input.replace("/message ", ""));
                }

                try {
                    oos.writeObject(message);
                    if (message.getType().equals(EXIT)) break;
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    oos.close();
                }
            }
            scanner.close();

            return null;
        }
    }

    static class ReadHandler implements Callable<Void> {

        private final Socket socket;

        public ReadHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws Exception {
            ObjectInputStream ois = null;
            try {
                while (true) {
                    ois = new ObjectInputStream(socket.getInputStream());
                    Message message = (Message) ois.readObject();
                    System.out.println(message.getMessage());
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                ois.close();
            }
        }
    }
}
