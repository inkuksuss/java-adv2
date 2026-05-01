package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class RealTextFileV1 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeStr = "abc\n가나다";
        System.out.println("===write===");
        System.out.println(writeStr);

        Path path = Path.of(PATH);
        Files.writeString(path, writeStr, StandardCharsets.UTF_8);
        String readString = Files.readString(path, StandardCharsets.UTF_8);

        System.out.println("===read===");
        System.out.println(readString);
    }
}
