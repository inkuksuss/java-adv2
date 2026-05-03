package network.tcp.v1;

import util.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.*;

public class ServerV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("server start");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("server socket start - port: " + PORT);

        Socket socket = serverSocket.accept();
        log("socket connect: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String received = input.readUTF();
        log("client -> server: " + received);

        String toSend = received + " world";
        output.writeUTF(toSend);
        log("client <- server: " + toSend);

        log("server close: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
