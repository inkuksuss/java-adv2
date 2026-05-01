package io.text;

import java.io.*;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV4 {

    static final int BUFFER_SIZE = 8 * 1024;

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("writeString = " + writeString);

        FileWriter fileWriter = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter, BUFFER_SIZE);
        bufferedWriter.write(writeString);
        bufferedWriter.close();


        StringBuilder sb = new StringBuilder();
        FileReader fileReader = new FileReader(FILE_NAME, UTF_8);
        BufferedReader bufferedReader = new BufferedReader(fileReader, BUFFER_SIZE);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        System.out.println(sb);
        bufferedReader.close();
    }
}

