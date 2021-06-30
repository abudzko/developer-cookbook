package com.budzko.cookbook.java.study.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ExchangerStudy {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Exchanger<List<String>> exchanger = new Exchanger<>();
        List<String> producerList = new ArrayList<>();
        List<String> consumerList = new ArrayList<>();
        executorService.execute(new Runnable() {
            List<String> holder = producerList;

            AtomicInteger atomicInteger = new AtomicInteger();

            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    for (int i = 0; i < 5; i++) {
                        holder.add(String.valueOf(atomicInteger.incrementAndGet()));
                    }
                    System.out.println("Producer - added:(" + holder.hashCode() + ")" + holder);
                    try {
                        Thread.sleep(1000);
                        holder = exchanger.exchange(holder);
                        System.out.println("Producer - exchanged:(" + holder.hashCode() + ")" + holder);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executorService.execute(new Runnable() {
            List<String> holder = consumerList;

            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        holder = exchanger.exchange(holder);
                        System.out.println("Consumer - exchanged:(" + holder.hashCode() + ")" + holder);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    holder.clear();
                    System.out.println("Consumer - cleaned:(" + holder.hashCode() + ")" + holder);
                }
            }
        });

        executorService.shutdown();
    }
}
