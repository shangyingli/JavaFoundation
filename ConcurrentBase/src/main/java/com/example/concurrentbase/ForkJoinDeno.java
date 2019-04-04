package com.example.concurrentbase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDeno {

    public static void main(String[] args) {
        new ForkJoinDeno();
    }

    public ForkJoinDeno() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 100);
        Future<Integer> finalResult = forkJoinPool.submit(countTask);
        try {
            System.out.println("最终结果为 : " + finalResult.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    class CountTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD= 2;
        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (end - start < THRESHOLD) {
                for (int i = start; i <= end ; i++) {
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);
                leftTask.fork();
                rightTask.fork();
                Integer leftResult = leftTask.join();
                Integer rightResult = rightTask.join();
                sum = leftResult + rightResult;
            }
            return sum;
        }
    }

}
