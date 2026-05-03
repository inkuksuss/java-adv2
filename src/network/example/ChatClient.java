package network.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", PORT);
            socket.setSoTimeout(3000);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("이름을 입력해주세요: ");
                String name = scanner.nextLine();
                dos.writeUTF(name);

                String response = dis.readUTF();

                if (name.equals(response)) {
                    System.out.printf("[%s] 닉네임이 저장되었습니다.", response);
                    break;
                }
            }

            ExecutorService es = Executors.newFixedThreadPool(2);
            es.submit(new ReadHandler(socket));
            es.submit(new WriteHandler(socket));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            socket.close();
        }
    }

    static class WriteHandler implements Callable<Void> {

        private final Socket socket;

        public WriteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws Exception {
            Scanner scanner = new Scanner(System.in);
            DataOutputStream output;

            while (true) {
                String input = scanner.nextLine();
                try {
                    output = new DataOutputStream(socket.getOutputStream());
                    output.writeUTF(input);
                    if ("exit".equals(input)) break;
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            output.close();
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
            DataInputStream input = null;

            try {
                input = new DataInputStream(socket.getInputStream());

                while (true) {
                    String read = input.readUTF();

                    if (read.startsWith("/join")) {
                        System.out.printf("[입장] %s님이 입장하셨습니다.", read.replace("/join ", ""));
                    }
                    else if (read.startsWith("/message")) {
                        System.out.printf("[메세지] %s", read.replace("/message ", ""));
                    }
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                input.close();
            }
        }
    }
}
