package com.raj.shashi.tmultihreading;

import java.util.ArrayList;
import java.util.List;

public class PC {

    private static List<String> list = new ArrayList<String>();

    public static class Producer extends Thread {

        private List<String> list;

        public Producer(List<String> list) {

            this.list = list;
        }

        @Override
        public void run() {

            while (true) {
                synchronized (this.list) {
                    if (this.list.size() > 10) {
                        try {
                            System.out.println("producer waiting");
                            this.list.wait();
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    } else {

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println("producer added");
                        System.out.println("list size is producer ");
                        this.list.add("producer");
                        this.list.notify();
                    }

                }
            }

        }

    }

    public static class Consumer extends Thread {

        private List<String> list;

        public Consumer(List<String> list) {

            this.list = list;
        }

        @Override
        public void run() {

            while (true) {

                synchronized (this.list) {

                    if (this.list.isEmpty()) {
                        try {
                            System.out.println("consumer waiting");
                            this.list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    else {

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        System.out.println("list size is consumer " + this.list.size());
                        System.out.println("cosumer removed " + this.list.remove(0));
                        this.list.notify();
                    }

                }

            }
        }

    }

    public static void main(String[] args) {

        Producer p = new Producer(list);
        Consumer c = new Consumer(list);

        p.start();
        c.start();
    }

}
