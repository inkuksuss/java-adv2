package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class ReadFileV4 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startMs = System.currentTimeMillis();

        byte[] bytes = fis.readAllBytes();
        fis.close();

        long endMs = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + bytes.length / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endMs - startMs) + "MS");
    }
}
