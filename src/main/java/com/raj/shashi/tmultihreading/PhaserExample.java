package com.raj.shashi.tmultihreading;

import java.util.concurrent.Phaser;

/**
 *
 * @author shashi
 *
 *         <p>
 *         phaser can be used as both countDownLatch and cyclicBarrier.
 *         </p>
 *
 */

public class PhaserExample {

    private static Phaser phaser = new Phaser(2);

    public static class Task implements Runnable {

        Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;

        }

        public void run() {
            this.phaser.arrive();
            System.out.println("Thread arrived" + Thread.currentThread().getName());

        }

    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Task(phaser));
        Thread t2 = new Thread(new Task(phaser));

        t1.start();
        t2.start();

        phaser.awaitAdvance(2);

        System.out.println("this phase has been completed");
    }

}
