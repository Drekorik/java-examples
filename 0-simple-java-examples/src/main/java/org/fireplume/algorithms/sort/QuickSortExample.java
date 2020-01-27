package org.fireplume.algorithms.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Берется некоторый элемент массива (желательно рандомом,
 * чтобы алгоритм выполнялся приблизительно одинаковые промежутки времени для любых наборов данных).
 * Элементы, которые находятся слева и больше по значению чем первоначальный, меняются местами с элементами,
 * которые находятся справа и меньше первоначального.
 * Аналогичная операция выполняется для наборов данных слева и справа от начального.
 * И так далее по рекурсии.
 */
public class QuickSortExample {
    public static int ARRAY_LENGTH = 30;
    private static List<Integer> array = new ArrayList<>();

    public static void initArray() {
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            array.add(new Random().nextInt(100));
        }
    }

    public static void printArray() {
        System.out.println(array.toString());
    }

    public static void main(String... args) {
        initArray();
        printArray();
        QuickSort.qSort(array);
        printArray();
    }
}

class QuickSort {
    public static void qSort(List<Integer> array) {
        int n = array.size();
        int i = 0;
        int j = n - 1;
        int x = array.get(j / 2);
        while (i <= j) {
            while (array.get(i) < x) {
                i++;
            }
            while (array.get(j) > x) {
                j--;
            }
            if (i <= j) {
                Collections.swap(array, i, j);
                i++;
                j--;
            }
        }
        if (j > 0) {
            qSort(array.subList(0, j + 1));
        }
        if (i < n) {
            qSort(array.subList(i, n));
        }
    }
}
