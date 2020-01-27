package org.fireplume.algorithms.fibonachi;

import java.math.BigInteger;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(fibRecursion(n));
        System.out.println(fibCycle(n));
    }

    private static BigInteger fibRecursion(int n) {
        return n < 2 ? BigInteger.valueOf(n) : fibRecursion(n - 1).add(fibRecursion(n - 2));
    }

    private static BigInteger fibCycle(int n) {
        if (n < 2)
            return BigInteger.valueOf(n);
        BigInteger nm1 = BigInteger.valueOf(1);
        BigInteger nm2 = BigInteger.valueOf(0);
        BigInteger fib = nm1.add(nm2);
        for (int i = 2; i < n; i++) {
            nm2 = nm1;
            nm1 = fib;
            fib = nm1.add(nm2);
        }
        return fib;
    }
}
