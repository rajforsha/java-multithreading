package com.raj.shashi.tmultihreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    private static CountDownLatch latch = new CountDownLatch(2);

    private static ExecutorService service = Executors.newCachedThreadPool();

    public static class MyThread implements Runnable {

        CountDownLatch latch;

        public MyThread(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            System.out.println("started");
            this.latch.countDown();
            System.out.println("countis "+this.latch.getCount());
            System.out.println("completed");
        }

    }

    public static void main(String[] args) {

        service.execute(new MyThread(latch));
        service.execute(new MyThread(latch));

        try {
            latch.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

}
