package com.budzko;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class AsyncService {

    @Getter
    private String status = "NEW";

    public void start(){
        var service = this;
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(2);
                service.status = "FINISH";
            }
        }).start();
    }
}
