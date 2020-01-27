package org.fireplume.algorithms.max_min_without_if;

public class MaxMinWithoutIf {
    public static void main(String[] args) {
        int x = 4;
        int y = 7;
        System.out.println("Max: " + getMax(x, y));
        System.out.println("Min: " + getMin(x, y));
    }

    private static int getMax(int x, int y) {
        return (int) ((x + y) / 2d + (Math.abs(x - y)) / 2d);
    }

    private static int getMin(int x, int y) {
        return (int) ((x + y) / 2d + (-Math.abs(x - y)) / 2d);
    }
}
