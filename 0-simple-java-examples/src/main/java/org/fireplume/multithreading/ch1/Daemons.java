package org.fireplume.multithreading.ch1;

import org.fireplume.multithreading.utils.ThreadsHelper;

/**
 * JVM will be shutted down when all NON DAEMON threads will end
 */
public class Daemons {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ": start");

        Thread usualThread = new Thread(new UsualThread());
        usualThread.start();

        DaemonThread daemonThread = new DaemonThread();
        daemonThread.start();

        System.out.println(Thread.currentThread() + ": stop");
    }

    private static class UsualThread implements Runnable {

        @Override
        public void run() {
            Thread.currentThread().setName("UsualThread");
            ThreadsHelper.cycleFunction();
        }
    }

    private static class DaemonThread extends Thread {

        public DaemonThread() {
            setDaemon(true);
        }

        @Override
        public void run() {
            Thread.currentThread().setName("DaemonThread");
            ThreadsHelper.cycleFunction();
        }
    }
}
