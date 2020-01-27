package org.fireplume.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloudjumper on 8/16/16.
 */
public class SortMergeExample {
    public static void main(String... args) {
        List<Integer> array = new ArrayList<>();
        array.add(8);
        array.add(3);
        array.add(2);
        array.add(5);
        array.add(4);
        array.add(68);
        List<Integer> sorted = MergeSort.doSort(array);
        System.out.println(sorted.toString());
    }
}

class MergeSort {
    public static List<Integer> doSort(List<Integer> array) {
        if (array.size() > 1) {
            List<Integer> l1 = doSort(array.subList(0, (array.size() / 2)));
            List<Integer> l2 = doSort(array.subList((array.size() / 2), array.size()));
            List<Integer> l3 = new ArrayList<>();
            int i = 0, j = 0;
            while (i < l1.size() || j < l2.size()) {
                if (i >= l1.size())
                    l3.add(l2.get(j++));
                else if (j >= l2.size())
                    l3.add(l1.get(i++));
                else {
                    if (l1.get(i) < l2.get(j))
                        l3.add(l1.get(i++));
                    else
                        l3.add(l2.get(j++));
                }
            }
            return l3;
        } else {
            return array;
        }
    }
}
