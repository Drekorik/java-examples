package org.fireplume.multithreading.ch3;

import org.fireplume.multithreading.utils.ThreadsHelper;

public class ThreadGroups {
    public static void main(String[] args) {
        ThreadGroup threadGroup1 = new ThreadGroup("ThreadGroup1");

        Thread thread1 = new Thread(
                threadGroup1,
                () -> ThreadsHelper.countDownWithPause(5),
                "Thread1");

        Thread thread2 = new Thread(
                threadGroup1,
                () -> ThreadsHelper.countDownWithPause(5),
                "Thread2");
        thread1.start();
        thread2.start();
    }
}
