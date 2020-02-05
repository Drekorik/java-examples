package org.fireplume.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ThreadsHelper {
    private ThreadsHelper() {
    }

    public static void pause(long millis) {
        try {
//            System.out.println("Gonna sleep for " + (double) millis/1_000 + " seconds");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void countDownWithPause(int times) {
        countDownWithPause(times, 1_000);
    }

    public static void countDownWithPause(int times, long pauseInMillis) {
        for (int i = times; i > 0; i--) {
            System.out.printf("%s:%s, shutdown in: %d%n",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getThreadGroup().getName(),
                    i);
            pause(pauseInMillis);
        }
    }

    public static void cycleFunction() {
        cycleFunction(0, 10_000_000, 100_000);
    }

    public static void cycleFunction(long printStep) {
        cycleFunction(0, 10_000_000, printStep);
    }

    public static void cycleFunction(long to, long printStep) {
        cycleFunction(0, to, printStep);
    }

    public static void cycleFunction(long from, long to, long printStep) {
        System.out.println(Thread.currentThread().getName() + " start");
        for (long i = from; i < to; i++) {
            if (i % printStep == 0) {
                System.out.println(i);
            }
        }
        System.out.println(100_000_000);
        System.out.println(Thread.currentThread().getName() + " stop");
    }

    public static <T> T getResultFromFuture(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
