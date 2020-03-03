package org.fireplume.multithreading.ch6ScheduledExecutors;

import org.fireplume.multithreading.utils.ThreadsHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutors {
    public static void main(String[] args) {
        ScheduledExecutorService executorService1 = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<String> schedule1 = executorService1.schedule(new SomeCallable(), 5, TimeUnit.SECONDS);
        System.out.println(ThreadsHelper.getResultFromFuture(schedule1));
        executorService1.shutdown();

        ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> schedule2 = executorService2.scheduleAtFixedRate(
                () -> ThreadsHelper.pause(4_000), 0, 2, TimeUnit.SECONDS);
        ThreadsHelper.pause(10_000);
        executorService2.shutdown();
    }

    private static class SomeCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            ThreadsHelper.pause(4_000);
            Thread thread = Thread.currentThread();
            return thread.getName() + ":" + thread.getThreadGroup().getName();
        }
    }
}
