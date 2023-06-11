package com.budzko.cookbook.java.study.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FutureStudy {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Void unused = cf1.get();
        log.info(String.valueOf(unused));

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "RESULT");
        cf2.whenComplete((s, throwable) -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Completed!");
        }).whenComplete((s, throwable) -> {
            log.info("Completed!!");
        }).whenCompleteAsync((s, throwable) -> {
            log.info("ASYNC - MAY NOT TO BE PRINTED!!!");
        });
        String result = cf2.get();
        log.info(result);
        log.info("EXIT");
    }
}
