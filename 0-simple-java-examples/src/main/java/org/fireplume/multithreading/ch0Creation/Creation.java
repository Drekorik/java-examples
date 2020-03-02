package org.fireplume.multithreading.ch0Creation;

import org.fireplume.multithreading.utils.ThreadsHelper;

public class Creation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread() + ": start");
        // #1
        Thread thread1 = new Thread(new ThreadFromRunnable());
        thread1.start();

//        thread1.join();

        //#2
        Thread thread2 = new ThreadFromThread();
        thread2.start();

//        thread2.join();

        //#3
        Thread thread3 = new Thread(() -> {
            Thread.currentThread().setName("Lambda (But still Runnable)");
            ThreadsHelper.cycleFunction();
        });
//        thread3.start();

        System.out.println(Thread.currentThread() + ": stop");

    }

    /**
     * #1
     */
    private static class ThreadFromRunnable implements Runnable {
        @Override
        public void run() {
            Thread.currentThread().setName("Runnable");
            ThreadsHelper.cycleFunction();
        }
    }

    /**
     * #2
     */
    private static class ThreadFromThread extends Thread {
        @Override
        public void run() {
            Thread.currentThread().setName("Thread");
            ThreadsHelper.cycleFunction();
        }
    }
}
