package org.fireplume.multithreading.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoin {

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        final ForkJoinAction action = new ForkJoinAction(new char[]{'1', 'q', 'w', 't', '2', '4', 'L'});
        forkJoinPool.execute(action);
        action.join();

        System.out.println("--------------------------------------------------");

        final List<Long> longs = new ArrayList<>();
        longs.add(5L);
        longs.add(6L);

        longs.add(1L);
        longs.add(4L);

        longs.add(1L);
        longs.add(7L);

        longs.add(9L);

        System.out.println(longs);
        final ForkJoinTaskExample task = new ForkJoinTaskExample(longs);
        forkJoinPool.execute(task);
        final List<Long> join = task.join();
        System.out.println(join);
    }

    private static class ForkJoinAction extends RecursiveAction {

        private final char[] chars;

        public ForkJoinAction(char[] chars) {
            this.chars = chars;
        }

        @Override
        protected void compute() {
            if (chars.length > 1) {
                System.out.println("Dividing, " + Thread.currentThread().getName());
                final char[] halfChars1 = Arrays.copyOfRange(this.chars, 0, chars.length / 2);
                final char[] halfChars2 = Arrays.copyOfRange(this.chars, chars.length / 2, chars.length);
                ForkJoinAction.invokeAll(
                        new ForkJoinAction(halfChars1),
                        new ForkJoinAction(halfChars2)
                );
            } else {
                System.out.println("Printing, " + Thread.currentThread().getName());
                System.out.println(chars[0]);
            }
        }
    }

    private static class ForkJoinTaskExample extends RecursiveTask<List<Long>> {

        private final List<Long> longs;

        public ForkJoinTaskExample(List<Long> longs) {
            if (longs.size() % 2 != 0) {
                longs.add(0L);
            }
            this.longs = longs;
        }

        @Override
        protected List<Long> compute() {
            if (longs.size() != 2) {
                System.out.println("Dividing, " + Thread.currentThread().getName());
                final ForkJoinTaskExample task1 = new ForkJoinTaskExample(longs.subList(0, longs.size() / 2));
                task1.fork();
                final ForkJoinTaskExample task2 = new ForkJoinTaskExample(longs.subList(longs.size() / 2, longs.size()));
                final List<Long> task2Res = task2.compute();
//                task2.fork();
//                final List<Long> task2Res = task2.join();

                final List<Long> task1Res = task1.join();
                final List<Long> resList = new ArrayList<>();
                resList.addAll(task1Res);
                resList.addAll(task2Res);
                return resList;
            } else {
                System.out.println("Summing, " + Thread.currentThread().getName());
                return Collections.singletonList(longs.get(0) + longs.get(1));
            }
        }
    }
}
