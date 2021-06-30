package com.budzko.cookbook.java.study.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierStudy {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int taskCount = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(taskCount);
        ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        AtomicInteger idGen = new AtomicInteger();
        for (int i = 0; i < taskCount; i++) {
            executorService.execute(new Runnable() {
                final int id = idGen.incrementAndGet();
                @Override
                public void run() {
                    try {
                        System.out.println("Task started:" + id);
                        cyclicBarrier.await();
                        Thread.sleep(2000);
                        System.out.println("Task:" + id + " after sleep");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        System.out.println("Main exited");
    }

}
