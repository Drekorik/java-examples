package org.fireplume.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloudjumper on 8/18/16.
 */
public class BubbleSortExample {
    public static void main(String... args) {
        List<Integer> array = new ArrayList<>();
        array.add(8);
        array.add(3);
        array.add(2);
        array.add(5);
        array.add(4);
        array.add(68);
        List<Integer> sorted = new BubbleSort().sort(array);
        System.out.println(sorted.toString());
    }
}

class BubbleSort {
    public List<Integer> sort(List<Integer> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - 1 - i; j++) {
                if (array.get(j).compareTo(array.get(j + 1)) > 0) {
                    Integer temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        return array;
    }
}
