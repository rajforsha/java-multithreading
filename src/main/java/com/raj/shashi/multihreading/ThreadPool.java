package com.raj.shashi.multihreading;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private int size;
    private LinkedBlockingQueue<Runnable> queue;
    private MyThread[] pool;

    public ThreadPool(int size) {

        System.out.println("size is" + size);
        this.size = size;
        this.queue = new LinkedBlockingQueue<>();
        System.out.println("queue is " + this.queue.size());
        this.pool = new MyThread[size];
        for (int i = 0; i < size; i++) {
            this.pool[i] = new MyThread(this.queue);
            this.pool[i].start();
        }
    }

    public void execute(Runnable runnable) {
        this.queue.add(runnable);
        synchronized (this.queue) {
            this.queue.notifyAll();
        }

    }

    public static void main(String[] args) {
        ThreadPool ob = new ThreadPool(4);
        ob.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("Hello");

            }
        });
    }

}
