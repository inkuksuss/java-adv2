package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("client start");
        Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
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



        log("connect close: " + socket);
        input.close();
        output.close();
        socket.close();
    }
}
