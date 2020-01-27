package org.fireplume.algorithms.swap;

public class Swap {
    public static void main(String[] args) {
        int a = 6;
        int b = 7;
        swapLogic(a, b);
        swapMath1(a, b);
        swapMath2(a, b);
    }

    private static void swapLogic(int a, int b) {
        System.out.println("Logic:");
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        System.out.printf("[a,b]=[%s,%s]%n", Integer.toBinaryString(a), Integer.toBinaryString(b));
        a = a ^ b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        System.out.printf("[a,b]=[%s,%s]%n", Integer.toBinaryString(a), Integer.toBinaryString(b));
        b = a ^ b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        System.out.printf("[a,b]=[%s,%s]%n", Integer.toBinaryString(a), Integer.toBinaryString(b));
        a = a ^ b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        System.out.printf("[a,b]=[%s,%s]%n", Integer.toBinaryString(a), Integer.toBinaryString(b));
    }

    private static void swapMath1(int a, int b) {
        System.out.println("Math:");
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        a = a + b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        b = a - b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        a = a - b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
    }

    private static void swapMath2(int a, int b) {
        System.out.println("Math:");
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        a = a * b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        b = a / b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
        a = a / b;
        System.out.printf("[a,b]=[%d,%d]%n", a, b);
    }
}
