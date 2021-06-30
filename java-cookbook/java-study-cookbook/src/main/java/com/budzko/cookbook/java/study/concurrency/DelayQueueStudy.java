package com.budzko.cookbook.java.study.concurrency;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

public class DelayQueueStudy {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedObject> delayQueue = new DelayQueue<>();
        int delayedObjectCount = 10;
        for (int i = 0; i < delayedObjectCount; i++) {
            delayQueue.add(new DelayedObject());
        }

        int i = 1;
        while (i != delayedObjectCount) {
            DelayedObject delayedObject = delayQueue.poll();
            if (delayedObject == null) {
                out.println("Wait:" + 100 + "ms");
                Thread.sleep(100);
                continue;
            }
            out.println(delayedObject);
            i++;
        }
    }

}

class DelayedObject implements Delayed {
    private static final Random DELAY_GEN = new Random();
    private static final AtomicInteger ID_GEN = new AtomicInteger();
    int id = ID_GEN.incrementAndGet();

    @Override
    public long getDelay(TimeUnit unit) {
        return DELAY_GEN.nextInt(3);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (o.getDelay(null) - getDelay(null));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}