package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV5 implements Runnable {

    private final Socket socket;

    public SessionV5(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(socket;
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {

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
        }
        catch (IOException e) {
            log(e);
        }
    }
}
