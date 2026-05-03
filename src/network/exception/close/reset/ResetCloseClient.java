package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("socket connect: " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // wait server close
        Thread.sleep(1000);

        output.write(1);

        Thread.sleep(1000);

        try {
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            // connection reset
            e.printStackTrace();
        }

        try {
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            // broken pipe
            e.printStackTrace();
        }
    }
}
