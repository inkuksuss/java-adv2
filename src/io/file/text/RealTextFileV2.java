package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class RealTextFileV2 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeStr = "abc\n가나다";
        System.out.println("===write===");
        System.out.println(writeStr);

        Path path = Path.of(PATH);
        Files.writeString(path, writeStr, StandardCharsets.UTF_8);

        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        lines.forEach(v -> System.out.println(v));
        lines.close();

//        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//        System.out.println("===read===");
//        for (int i = 0; i < lines.size(); i++) {
//            System.out.println((i + 1) + ": " + lines.get(i));
//        }
    }
}
