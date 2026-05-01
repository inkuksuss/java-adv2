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

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FILE_NAME, false), BufferedConst.BUFFER_SIZE);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILE_NAME));

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
        bos.close();

        byte[] bytes = bis.readAllBytes();
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));

        bis.close();
        es.close();
    }

    static class MyThread implements Callable<Boolean> {

        private final int key;
        private final OutputStream os;
        private final byte[] bytes = new byte[10];

        public MyThread(int key, OutputStream os) throws FileNotFoundException {
            this.key = key;
            this.os = os;
        }

        @Override
        public Boolean call() throws Exception {
            Thread.currentThread().sleep(100);
            for (int i = 0; i < 10; i++) {
                Arrays.fill(bytes, (byte) this.key);
                os.write(bytes);
            }

            return null;
        }
    }
}
