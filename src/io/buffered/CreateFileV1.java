package io.buffered;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFileV1 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);

        long startMs = System.currentTimeMillis();

        for (int i = 0; i < BufferedConst.FILE_SIZE; i++) {
            fos.write(1);
        }
        fos.close();

        long endMs = System.currentTimeMillis();

        System.out.println("File created: " + BufferedConst.FILE_NAME);
        System.out.println("File size: " + BufferedConst.FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endMs - startMs) + "MS");
    }
}
