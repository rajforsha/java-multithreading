package com.raj.shashi.tmultihreading;

import java.util.concurrent.Semaphore;

/**
 *
 * @author shashi
 *         <p>
 *         unlike synchronized , only one thread can be allowed to access
 *         critical section we can use semaphore to allow n number of thread to
 *         access critical section.
 *         </p>
 *
 */

public class SemaphoreExample {

    private static Semaphore sempahore = new Semaphore(2);

    public static class Task implements Runnable {

        Semaphore sempahore;

        public Task(Semaphore sempahore) {
            this.sempahore = sempahore;
        }

        public void run() {

            this.sempahore.acquireUninterruptibly();
            System.out.println("thread name is " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.sempahore.release();
        }

    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Task(sempahore));
        Thread t2 = new Thread(new Task(sempahore));
        Thread t3 = new Thread(new Task(sempahore));
        Thread t4 = new Thread(new Task(sempahore));
        Thread t5 = new Thread(new Task(sempahore));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

}
