package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("path = " + file.getPath());

        System.out.println("absolute path = " + file.getAbsolutePath()); // 절대 경로
        System.out.println("canonical path = " + file.getCanonicalPath()); // 정규 경로

        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println((file1.isFile() ? "F" : "D") + " | " + file1.getName());
        }
    }
}
