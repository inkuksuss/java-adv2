package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        FileWriter fileWriter = new FileWriter(FILE_NAME, UTF_8);
        fileWriter.write(writeString);
        fileWriter.close();


        FileReader fileReader = new FileReader(FILE_NAME, UTF_8);
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fileReader.read()) != -1) {
            sb.append((char) ch);
        }
        System.out.println("sb = " + sb);
        fileReader.close();
    }
}
