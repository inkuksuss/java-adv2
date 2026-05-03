package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("server start");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("server socket start - port: " + PORT);

        Socket socket = serverSocket.accept();
        log("socket connect: " + socket);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        while (true) {
            String received = input.readUTF();
            log("client -> server: " + received);

            if ("exit".equals(received)) {
                break;
            }

            String toSend = received + " world";
            output.writeUTF(toSend);
            log("client <- server: " + toSend);
        }

        log("server close: " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
