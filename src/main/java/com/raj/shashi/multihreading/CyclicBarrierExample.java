package com.raj.shashi.multihreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author shashi
 *         <p>
 *         unlike countDownLatch , cyclic barrier can be reused . in simple
 *         words once the barrier is reached , it repeats again
 *         </p>
 *
 */

public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static class Task implements Runnable {

        CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {

            while (true) {
                System.out.println("Thread arrived is " + Thread.currentThread().getName());
                try {
                    this.cyclicBarrier.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(cyclicBarrier));
        Thread t2 = new Thread(new Task(cyclicBarrier));

        t1.start();
        t2.start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
