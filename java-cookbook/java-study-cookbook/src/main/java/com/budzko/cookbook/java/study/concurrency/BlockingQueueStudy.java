package com.budzko.cookbook.java.study.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueStudy {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger idGen = new AtomicInteger();
        int nThreads = 1;
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        executorService.execute(new Runnable() {
            int id = idGen.incrementAndGet();

            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        String s = queue.take();
                        System.out.println("Id:" + id + ", " + s);
                        Thread.currentThread().interrupt();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("New task");
            }
        });
        Thread.sleep(2000);
        queue.add("1");
        executorService.shutdown();


    }
}
