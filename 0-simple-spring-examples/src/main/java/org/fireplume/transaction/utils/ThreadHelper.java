package org.fireplume.transaction.utils;

import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ThreadHelper {
    public static void sleep(long t) {
        try {
            System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void print(Object o) {
        System.out.println(Thread.currentThread().getName() + " | " + o.toString());
    }
}
