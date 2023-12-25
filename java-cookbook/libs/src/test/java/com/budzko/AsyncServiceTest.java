package com.budzko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

class AsyncServiceTest {

    public static final String FINISH_STATUS = "FINISH";

    @Test
    void test() {
        var service = new AsyncService();
        service.start();
        await()
                .atMost(Duration.ofSeconds(10))
                .pollInterval(Duration.ofMillis(100))
                .until(service::getStatus, equalTo(FINISH_STATUS));
        Assertions.assertEquals(FINISH_STATUS, service.getStatus());
    }
}
