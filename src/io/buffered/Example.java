package io.buffered;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static io.buffered.BufferedConst.FILE_NAME;

public class Example {


    public static void main(String[] args) throws Exception {

        ExecutorService es = Executors.newFixedThreadPool(10);
        FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BufferedConst.BUFFER_SIZE);
        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedInputStream bis = new BufferedInputStream(fis);

        List<Callable> list = new ArrayList<>();
        List<Future<Boolean>> fList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new MyThread(i, bos));
        }

        list.stream().forEach(t -> {
            Future ft = es.submit(t);
            fList.add(ft);
        });

        for (Future<Boolean> booleanFuture : fList) {
            booleanFuture.get();
        }

        byte[] bytes = bis.readAllBytes();
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));

        bos.close();
        bis.close();
        es.close();
    }

    static class MyThread implements Callable<Boolean> {

        private final int key;
        private final OutputStream os;
        private byte[] bytes;

        public MyThread(int key, OutputStream os) throws FileNotFoundException {
            this.key = key;
//            this.os = new BufferedOutputStream(new FileOutputStream(FILE_NAME));
//            this.os = new FileOutputStream(FILE_NAME);
            this.os = os;
            this.bytes = new byte[10];
        }

        @Override
        public Boolean call() throws Exception {
            for (int i = 0; i < 10; i++) {
                bytes[i] = (byte) this.key;
            }

            System.out.println(Arrays.toString(bytes));

            os.write(bytes);
            return null;
        }
    }
}
