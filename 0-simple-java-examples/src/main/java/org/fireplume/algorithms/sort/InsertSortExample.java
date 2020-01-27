package org.fireplume.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloudjumper on 8/18/16.
 */
public class InsertSortExample {
    public static void main(String... args) {
        List<Integer> array = new ArrayList<>();
        array.add(8);
        array.add(3);
        array.add(2);
        array.add(5);
        array.add(4);
        array.add(68);
        List<Integer> sorted = new InsertSort().sort(array);
        System.out.println(sorted.toString());
    }
}

class InsertSort {
    public List<Integer> sort(List<Integer> array) {
        for (int j = 1; j < array.size(); j++) {
            Integer key = array.get(j);
            int i = j - 1;
            while (i >= 0 && array.get(i) > key) {
                array.set(i + 1, array.get(i));
                i = i - 1;
                System.out.println("\t" + array);
            }
            array.set(i + 1, key);
            System.out.println(array);
        }
        return array;
    }
}
