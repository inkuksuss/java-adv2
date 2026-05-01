package io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class NewFilePath {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/..");

        System.out.println("path = " + file);

        System.out.println("absolute path = " + file.toAbsolutePath()); // 절대 경로
        System.out.println("canonical path = " + file.toRealPath()); // 정규 경로

        Stream<Path> pathStream = Files.list(file);
        List<Path> list = pathStream.toList();
        pathStream.close();

        list.forEach(path -> System.out.println((Files.isRegularFile(path) ? "F" : "D") + " | " + path.getFileName()));
    }
}
