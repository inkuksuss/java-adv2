package io.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.text.TextConst.*;
import static java.nio.charset.StandardCharsets.*;

public class ReaderWriterMainV1 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        byte[] writes = writeString.getBytes(UTF_8);
        System.out.println("writes = " + writeString);
        System.out.println("bytes = " + Arrays.toString(writes));

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writes);
        fos.close();

        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readAllBytes = fis.readAllBytes();
        fis.close();

        String s = new String(readAllBytes, UTF_8);
        System.out.println("readAllBytes = " + Arrays.toString(readAllBytes));
        System.out.println("s = " + s);
    }
}
