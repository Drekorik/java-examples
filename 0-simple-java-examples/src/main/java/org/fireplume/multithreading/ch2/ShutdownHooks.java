package org.fireplume.multithreading.ch2;

import org.fireplume.multithreading.utils.ThreadsHelper;

/**
 * - Let the shutdown hook set some AtomicBoolean (or volatile boolean) "keepRunning" to false
 * - (Optionally, .interrupt the working threads if they wait for data in some blocking call)
 * - Wait for the working threads (executing writeBatch in your case) to finish, by calling
 * the Thread.join() method on the working threads.
 * - Terminate the program
 */
public class ShutdownHooks {
    private static volatile boolean keepRunning = true;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ": start");

        Thread hook = new Thread(() -> {
            System.out.println("Running hook");
            keepRunning = false;
        });
        hook.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(hook);

        for (int i = 8; i > 0 && keepRunning; i--) {
            System.out.println(Thread.currentThread().getName() + ", shutdown in: " + i);
            ThreadsHelper.pause(1_000);
        }

        System.out.println(Thread.currentThread() + ": stop");
    }
}
