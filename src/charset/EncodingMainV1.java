package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodingMainV1 {

    public static final Charset EUC_KR = Charset.forName("EUC-KR");
    public static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", StandardCharsets.US_ASCII);
        encoding("A", StandardCharsets.ISO_8859_1);
        encoding("A", StandardCharsets.UTF_8);
        encoding("A", StandardCharsets.UTF_16BE);

        encoding("가", StandardCharsets.UTF_8);
        encoding("가", StandardCharsets.UTF_16BE);

        String str = "hello";
        byte[] bytes = str.getBytes();
        System.out.println("bytes = " + Arrays.toString(bytes));
    }

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] encoding -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }


}
