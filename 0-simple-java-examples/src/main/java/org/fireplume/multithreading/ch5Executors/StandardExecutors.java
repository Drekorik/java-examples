package org.fireplume.multithreading.ch5Executors;

import org.fireplume.multithreading.utils.ThreadsHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StandardExecutors {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //#1
        ExecutorService singleThread = Executors.newSingleThreadExecutor();
        Future<String> future1 = singleThread.submit(new SomeCallable());
        Future<String> future2 = singleThread.submit(new SomeCallable());
        System.out.println(future1.get());
        System.out.println(future2.get());
        singleThread.shutdown();

        //#2
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        Future<String> future3 = fixedPool.submit(new SomeCallable());
        Future<String> future4 = fixedPool.submit(new SomeCallable());
        System.out.println(future3.get());
        System.out.println(future4.get());
        fixedPool.shutdown();

        //#3
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        List<Future<String>> futures1 = new ArrayList<>();
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        Iterator<Future<String>> futureIterator1 = futures1.iterator();
        while (futureIterator1.hasNext()) {
            Future<String> future = futureIterator1.next();
            System.out.println("T: " + ThreadsHelper.getResultFromFuture(future));
            futureIterator1.remove();
        }
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futures1.add(cachedPool.submit(new SomeCallable()));
        futureIterator1 = futures1.iterator();
        while (futureIterator1.hasNext()) {
            Future<String> future = futureIterator1.next();
            System.out.println("T: " + ThreadsHelper.getResultFromFuture(future));
            futureIterator1.remove();
        }
        cachedPool.shutdown();

        //#4
        BlockingQueue<Runnable> runnables = new ArrayBlockingQueue<>(10, true);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                10,
                30,
                TimeUnit.SECONDS,
                runnables);
        List<Future<String>> futures2 = new ArrayList<>();
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        futures2.add(threadPoolExecutor.submit(new SomeCallable()));
        Iterator<Future<String>> futureIterator2 = futures2.iterator();
        while (futureIterator2.hasNext()) {
            Future<String> future = futureIterator2.next();
            System.out.println("T: " + ThreadsHelper.getResultFromFuture(future));
            futureIterator2.remove();
        }
        threadPoolExecutor.shutdown();
    }

    private static class SomeCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            ThreadsHelper.pause(2_000);
            Thread thread = Thread.currentThread();
            return thread.getName() + ":" + thread.getThreadGroup().getName();
        }
    }
}
