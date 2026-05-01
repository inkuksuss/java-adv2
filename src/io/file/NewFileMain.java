package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFileMain {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path dir = Path.of("temp/exampleDir");

        System.out.println("file = " + Files.exists(file));

        try {
            Files.createFile(file);
            System.out.println("create file");
        }
        catch (FileAlreadyExistsException e) {
            System.out.println("file already exists");
        }


        try {
            Files.createDirectory(dir);
            System.out.println("create dir");
        }
        catch (FileAlreadyExistsException e) {
            System.out.println("dir already exists");
        }

//        Files.delete(file);
//        System.out.println("deleted");

        System.out.println("is regular file" + Files.isRegularFile(file));
        System.out.println("is dir" + Files.isDirectory(dir));
        System.out.println("file name: " + file.getFileName());
        System.out.println("file size: " + Files.size(file) + "bytes");

        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("file moved/renamed");

        System.out.println("last mod: " + Files.getLastModifiedTime(newFile));
        BasicFileAttributes attr = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("=====attr=====");
        System.out.println("create time: " + attr.creationTime());
        System.out.println("is dir: " + attr.isDirectory());
        System.out.println("is file: " + attr.isRegularFile());
        System.out.println("symbolic: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());
    }
}
