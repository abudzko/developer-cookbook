package com.budzko.cookbook.java.study.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStateStudy {
    public static void main(String[] args) throws Exception {
//        newState();
//        runnableState();
//        blockedState1();
//        timedWaitingState();
//        timedWaitingState2();
//        interruptSynced();
//        reentrantLock();
        waitNotify();
    }

    private static void waitNotify() throws InterruptedException {
        int threadCount = 5;
        Object lock = new Object();
        AtomicInteger idGen = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new Runnable() {
                int id = idGen.incrementAndGet();

                @Override
                public void run() {
                    System.out.println("Run:" + id);
                    synchronized (lock) {
                        try {
                            lock.wait(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Continue " + id);
                    }
                }
            });
        }

        Thread.sleep(1000);
        synchronized (lock) {
            lock.notify();
//            lock.notifyAll();
        }
        executorService.shutdown();

    }

    private static void reentrantLock() throws InterruptedException {
        class LockWrapper {
            void lock() {
                reentrantLock.lock();
            }

            void unlock() {
                reentrantLock.unlock();
            }

            void forceUnlock() {
                reentrantLock = null;
            }

            ReentrantLock reentrantLock = new ReentrantLock();
        }
        LockWrapper lockWrapper = new LockWrapper();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1:run");
                try {
                    lockWrapper.lock();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1:exit");
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2:run");
                lockWrapper.unlock();//IllegalMonitorStateException
                System.out.println("T2:exit");
            }
        });
        t2.start();
    }

    private static void interruptSynced() throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("T1:synchronized");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(100);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("T2:synchronized");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
        Thread.sleep(100);
        t2.interrupt();
    }

    private static void timedWaitingState() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(500);
        System.out.println(t.getState());

    }

    private static void timedWaitingState2() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o = new Object();
                try {
                    synchronized (o) {
                        o.wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(500);
        System.out.println(t.getState());

    }

    private static void newState() {
        Thread t = new Thread();
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
    }

    private static void runnableState() {
        Thread t = new Thread();
        t.start();
        System.out.println(t.getState());
    }

    private static void blockedState1() throws Exception {
        Object lock = new Object();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("T2:in synchronized");
                    System.out.println("T2 state:" + Thread.currentThread().getState());
                }
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("T1:in synchronized");
                    int period = 0;
                    while (period < 1000) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("T2 state:" + t2.getState());
                        period += 500;
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }


}
