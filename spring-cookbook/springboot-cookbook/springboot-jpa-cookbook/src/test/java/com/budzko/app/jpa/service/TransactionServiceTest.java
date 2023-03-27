package com.budzko.app.jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest
/**
 * spring:
 *   datasource:
 *     hikari:
 *       maximum-pool-size: 10 #maximum pool size
 * Long transactions can acquire all connections
 */
class TransactionServiceTest {

    protected static final int TASK_DURATION_MS = 5000;
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DataSource dataSource;

    private static Runnable buildLongTask() {
        return () -> {
            try {
                Thread.sleep(TASK_DURATION_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static void waitForResult(List<Future<String>> futureList) {
        futureList.forEach(future -> {
            try {
                log.info("Task {} - completed", future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    private Callable<String> buildTask() {
        return new Callable<>() {
            private final String id = UUID.randomUUID().toString();

            @Override
            public String call() {
                transactionService.execute(id, buildLongTask());
                return id;
            }
        };
    }

    @Test
    void testTransactionService() {
        int taskCount = 10;
        List<Future<String>> futureList = new LinkedList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        for (int i = 0; i < taskCount; i++) {
            futureList.add(executorService.submit(buildTask()));
        }

        waitForResult(futureList);
    }

    @Test
    void test() {
        log.info("");
    }
}
