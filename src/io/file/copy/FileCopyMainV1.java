package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV1 {

    private static final int FILE_SIZE = 200 * 1024 * 1024; // 200mb

    public static void main(String[] args) throws IOException {
        String fileName = "temp/copy.dat";
        String newFileName = "temp/copy_new.dat";
        long startT = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(fileName);
        FileOutputStream fos = new FileOutputStream(newFileName);

        byte[] bytes = fis.readAllBytes();
        fos.write(bytes);
        fis.close();
        fos.close();

        long endT = System.currentTimeMillis();
        System.out.println("time taken: " + (endT - startT) + "ms");
    }

}
