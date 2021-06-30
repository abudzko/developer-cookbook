package com.budzko.cookbook.java.study.concurrency;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerStudy {
    public static void main(String[] args) {
        lockWithAtomicInteger();
    }

    private static void lockWithAtomicInteger() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Task task1 = new Task();
        executorService.execute(task1);
        Task task2 = new Task();
        executorService.execute(task2);
    }

}

class Task implements Runnable {

    private static NonBlockingLock nonBlockingLock = new NonBlockingLock();
    private static final AtomicInteger ID_GEN = new AtomicInteger();
    private final int id = ID_GEN.incrementAndGet();
    private static final int SLEEP_MS = 5000;

    @Override

    public void run() {
        while (!Thread.interrupted()) {
            long start = System.currentTimeMillis();
            try {
                nonBlockingLock.lock();
                System.out.println("T" + id + ":sleep " + SLEEP_MS + " ms");
                Thread.sleep(SLEEP_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                nonBlockingLock.unlock();
                long end = System.currentTimeMillis();
                System.out.println("T" + id + " duration " + (end - start) + " ms");
            }
        }
    }
}


class NonBlockingLock {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void lock() {
        while (!atomicInteger.compareAndSet(0, 1)) {
        }
    }

    public void unlock() {
        while (!atomicInteger.compareAndSet(1, 0)) {
        }
    }
}