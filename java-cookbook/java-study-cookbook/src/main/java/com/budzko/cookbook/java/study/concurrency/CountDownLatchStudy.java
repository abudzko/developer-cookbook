package com.budzko.cookbook.java.study.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Main thread initializes state, starts {@code THREAD_COUNT} threads, perform the work during {@code WORK_TIME} sec
 * {@code THREAD_COUNT} threads waits for main thread
 * Main thread completes the work, notifies {@code THREAD_COUNT} threads that they can proceed and starts to wait for other threads
 * {@code THREAD_COUNT} threads perform the work during {@code WORK_TIME} sec,
 * {@code THREAD_COUNT} threads when completes work {@code THREAD_COUNT} time notify main thread: {@code doneSignal.countDown()}
 * that main thread can proceed.
 */
public class CountDownLatchStudy {

    private static final int WORK_TIME = 2000;
    private static final int THREAD_COUNT = 5;
    private static ThreadLocal<Long> timestamp = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);

        CountDownLatch doneSignal = new CountDownLatch(THREAD_COUNT);
        Random rand = new Random();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new Runnable() {
                final int id = rand.nextInt();

                @Override
                public void run() {
                    try {
                        print("Await:" + id);
                        startSignal.await();
                        doWork();
                        print("CountDown:" + id);
                        doneSignal.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
        print("Main:do work.");
        doWork();
        print("Main:work completed.");
        print("Main:startSignal.countDown()");
        startSignal.countDown();
        print("Main:doneSignal.await()");
        doneSignal.await();
        print("Main:completed.");
    }

    private static void print(String s) {
        Long previousTimestamp = getPreviousTimestampOrCurrent();
        Long currentTimestamp = System.currentTimeMillis();
        System.out.println(currentTimestamp - previousTimestamp + " ms:" + s);
        updatePreviousTimestamp();
    }

    private static void updatePreviousTimestamp() {
        timestamp.set(System.currentTimeMillis());
    }

    private static Long getPreviousTimestampOrCurrent() {
        Long t = timestamp.get();
        if (t == null) {
            t = System.currentTimeMillis();
        }
        return t;
    }

    private static void doWork() {
        try {
            Thread.sleep(WORK_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
