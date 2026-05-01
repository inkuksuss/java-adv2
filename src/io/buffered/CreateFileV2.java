package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        long startMs = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bufferIdx = 0;
        for (int i = 0; i < FILE_SIZE; i++) {
            buffer[bufferIdx++] = 1;

            if (bufferIdx == BUFFER_SIZE) {
                fos.write(buffer);
                bufferIdx = 0;
            }
        }

        if (bufferIdx > 0) {
            fos.write(buffer, 0 , bufferIdx);
        }

        fos.close();

        long endMs = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endMs - startMs) + "MS");
    }
}
