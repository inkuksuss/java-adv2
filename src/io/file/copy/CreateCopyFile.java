package io.file.copy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateCopyFile {

    private static final int FILE_SIZE = 200 * 1024 * 1024; // 200mb

    public static void main(String[] args) throws IOException {
        String fileName = "temp/copy.dat";
        long startT = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] bytes = new byte[FILE_SIZE];
        fos.write(bytes);
        fos.close();

        long endT = System.currentTimeMillis();

        System.out.println("file crated: " + fileName);
        System.out.println("file size: " + FILE_SIZE / 1024 / 1024  + "MB");
        System.out.println("time taken: " + (endT - startT) + "ms");
    }

}
