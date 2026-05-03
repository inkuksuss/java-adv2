package network.tcp.v1;

import util.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.*;

public class ClientV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("client start");
        Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("socket connect: " + socket);

        String toSend = "Hello";
        output.writeUTF(toSend);
        log("client -> server: " + toSend);

        String received = input.readUTF();
        log("client <- server: " + received);

        log("connect close: " + socket);
        input.close();
        output.close();
        socket.close();
    }
}
