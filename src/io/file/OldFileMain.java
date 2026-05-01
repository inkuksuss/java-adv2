package io.file;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

public class OldFileMain {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File dir = new File("temp/exampleDir");

        System.out.println("file exists: " + file.exists());

        boolean newFile = file.createNewFile();
        System.out.println("newFile = " + newFile);

        boolean mkdir = dir.mkdir();
        System.out.println("mkdir = " + mkdir);

//        boolean delete = file.delete();
//        System.out.println("delete = " + delete);

        System.out.println("is file?: " + file.isFile());
        System.out.println("is dir?: " + dir.isDirectory());
        System.out.println("file name: " + file.getName());
        System.out.println("file size: " + file.length() + "byte");

        File newFiled = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFiled);
        System.out.println("renamed = " + renamed);

        long lastModified = newFiled.lastModified();
        System.out.println("lastModified = " + new Date(lastModified));
    }
}
