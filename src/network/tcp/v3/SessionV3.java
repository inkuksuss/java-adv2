package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV3 implements Runnable {

    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
