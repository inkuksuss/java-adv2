package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV5 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("client start");

        try (Socket socket = new Socket("localhost", PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            log("socket connect: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("text : ");
                String toSend = scanner.nextLine();
                output.writeUTF(toSend);
                log("client -> server: " + toSend);

                String received = input.readUTF();
                log("client <- server: " + received);


                if ("exit".equals(toSend)) {
                    break;
                }
            }
        } catch (IOException e) {
            log(e);
        }
    }
}
