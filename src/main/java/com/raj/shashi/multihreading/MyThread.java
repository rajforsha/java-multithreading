package com.raj.shashi.multihreading;

import java.util.concurrent.LinkedBlockingQueue;

public class MyThread extends Thread {

    LinkedBlockingQueue<Runnable> queue;

    public MyThread(LinkedBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        synchronized (this.queue) {

            while (this.queue.isEmpty()) {
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Runnable task = this.queue.take();
                task.run();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
