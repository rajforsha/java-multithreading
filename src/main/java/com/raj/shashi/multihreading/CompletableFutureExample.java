package com.raj.shashi.multihreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author shashi
 *         <p>
 *         Completablefuture is simliar to what we have promise in js
 *         supplyAsync-> async method returns some result runAsync-> async
 *         method doesn't return apply-> process and transform the result of
 *         previous future
 *         </p>
 *
 */

public class CompletableFutureExample {

    public static String getHello() {

        System.out.println("getHello called");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello";
    }

    public static String getWorld(String hello) {

        System.out.println("getWorld called");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hello + "World";
    }

    public static void main(String[] args) {

        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> getHello())
                .thenApply((val) -> getWorld(val));

        try {
            System.out.println("result is " + result.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
