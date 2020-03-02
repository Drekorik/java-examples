package org.fireplume.multithreading.ch4CallableThreads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class CallableThreads {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread() + ": start");

        // #1
        RunnableFuture<Integer> futureTask1 = new FutureTask<>(new MyCallable(3, 5));
        new Thread(futureTask1).start();
        System.out.println("The result is: " + futureTask1.get());

        // #2
        int a = 5;
        int b = 8;
        RunnableFuture<Integer> futureTask2 = new FutureTask<>(() -> Math.max(a, b));
        new Thread(futureTask2).start();
        System.out.println("The result is: " + futureTask2.get());

        System.out.println(Thread.currentThread() + ": stop");
    }

    /**
     * #1
     */
    private static class MyCallable implements Callable<Integer> {
        private int a;
        private int b;

        public MyCallable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer call() throws Exception {
            return Math.max(a, b);
        }
    }
}
