package com.raj.shashi.tmultihreading;

import java.util.HashSet;

/**
 *
 * @author shashi
 * <p>
 * ALternative to synchronization we have volatile which is non blocking
 * and if we have atomic operation we can use volatile object and primitive data type.
 * </p>
 *
 */

public class VolatileExample {

    private static volatile HashSet<Double> hashSet = new HashSet<Double>();

    public static class MyThread extends Thread {

        @Override
        public void run() {

            while (true) {

                System.out.println("name is " + Thread.currentThread().getName());
                hashSet.add(Math.random());
                System.out.println(hashSet.size());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        MyThread t1 = new MyThread();
        t1.setName("1st");
        MyThread t2 = new MyThread();
        t2.setName("2nd");
        MyThread t3 = new MyThread();
        t3.setName("3rd");

        MyThread t4 = new MyThread();
        t4.setName("4th");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
