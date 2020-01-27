package org.fireplume.multithreading.ch7;

import org.fireplume.multithreading.utils.ThreadsHelper;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * not done
 */
public class Locks {
    private static boolean keepWork = true;

    public static void main(String[] args) throws InterruptedException {
        //#1
//        AnyDataClass anyDataClass = new AnyDataClass();
//        Thread thread1 = new Thread(new AnyDataWorker(anyDataClass), "Thread1");
//        Thread thread2 = new Thread(new AnyDataWorker(anyDataClass), "Thread2");
//        thread1.start();
//        thread2.start();
//        ThreadsHelper.pause(10_000);
//        keepWork = false;
//        thread1.join();
//        thread2.join();
//        System.out.println("---\t---\t---");
//
//        keepWork = true;
//        anyDataClass = new AnyDataClass();
//        Thread thread3 = new Thread(new AnyDataWorkerWithLock(anyDataClass), "Thread3");
//        Thread thread4 = new Thread(new AnyDataWorkerWithLock(anyDataClass), "Thread4");
//        thread3.start();
//        thread4.start();
//        ThreadsHelper.pause(10_000);
//        keepWork = false;
//        thread3.join();
//        thread4.join();

        //#2
        keepWork = true;
        Queue<String> strings = new LinkedList<>();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Thread threadRead = new Thread(new Consumer(strings, readWriteLock.readLock()), "ThreadRead");
        Thread threadWrite = new Thread(new Producer(strings, readWriteLock.writeLock()), "ThreadWrite");
        threadRead.start();
        threadWrite.start();
        ThreadsHelper.pause(20_000);
        keepWork = false;
    }

    private static class AnyDataClass {
        private int data;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    private static class AnyDataWorker implements Runnable {
        private static final Random RANDOM = new Random();
        private AnyDataClass anyDataClass;

        public AnyDataWorker(AnyDataClass anyDataClass) {
            this.anyDataClass = anyDataClass;
        }

        @Override
        public void run() {
            while (keepWork) {
                String threadName = Thread.currentThread().getName();
                int before = anyDataClass.getData();
                System.out.println(threadName + "\tWas:\t" + before);
                int data = RANDOM.nextInt(10_000);
                System.out.println(threadName + "\tSet:\t" + data);
                anyDataClass.setData(data);
                System.out.println(threadName + "\tWork\t");
                ThreadsHelper.pause((1 + RANDOM.nextInt(3)) * 1000);
                int after = anyDataClass.getData();
                System.out.println(threadName + "\tNow:\t" + after);
                if (after != data) {
                    System.out.println(threadName + "\tConcurrentError");
                }
                ThreadsHelper.pause(2_000);
            }
        }
    }

    private static class AnyDataWorkerWithLock implements Runnable {
        private static final ReentrantLock LOCK = new ReentrantLock(true);
        private static final Random RANDOM = new Random();
        private AnyDataClass anyDataClass;

        public AnyDataWorkerWithLock(AnyDataClass anyDataClass) {
            this.anyDataClass = anyDataClass;
        }

        @Override
        public void run() {
            while (keepWork) {
                LOCK.lock();
                String threadName = Thread.currentThread().getName();
                int before = anyDataClass.getData();
                System.out.println(threadName + "\tWas:\t" + before);
                int data = RANDOM.nextInt(10_000);
                System.out.println(threadName + "\tSet:\t" + data);
                anyDataClass.setData(data);
                System.out.println(threadName + "\tWork\t");
                ThreadsHelper.pause((1 + RANDOM.nextInt(3)) * 1000);
                int after = anyDataClass.getData();
                System.out.println(threadName + "\tNow:\t" + after);
                if (after != data) {
                    System.out.println(threadName + "\tConcurrentError");
                }
                LOCK.unlock();
                ThreadsHelper.pause(2_000);
            }
        }
    }

    private static class Consumer implements Runnable {
        private Queue<String> strings;
        private Lock lock;

        public Consumer(Queue<String> strings, Lock lock) {
            this.strings = strings;
            this.lock = lock;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (keepWork || !strings.isEmpty()) {
                ThreadsHelper.pause(random.nextInt(5) * 1000);
                if (strings.isEmpty()) {
                    System.out.println("Producer: Nothing to read");
                    continue;
                }
                lock.lock();
                String str = strings.poll();
                System.out.println("Producer: I have consumed: " + str);
                lock.unlock();
            }
            System.out.println("Consumer done");
        }
    }

    private static class Producer implements Runnable {
        private Queue<String> strings;
        private Lock lock;

        public Producer(Queue<String> strings, Lock lock) {
            this.strings = strings;
            this.lock = lock;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (keepWork) {
                ThreadsHelper.pause(random.nextInt(5) * 1000);
                lock.lock();
                String str = LocalTime.now().toString();
                System.out.println("Producer: I have produced: " + str);
                strings.add(str);
                lock.unlock();
            }
            System.out.println("Producer done");
        }
    }
}
