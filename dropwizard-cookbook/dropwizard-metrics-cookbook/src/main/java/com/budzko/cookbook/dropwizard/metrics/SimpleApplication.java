package com.budzko.cookbook.dropwizard.metrics;

import java.util.Random;

public class SimpleApplication {

    private MonitoringService monitoringService = new MonitoringService();

    public static void main(String[] args) throws Exception {
        new SimpleApplication().start();
    }

    private void start() throws Exception {
        Random random = new Random();
        while (true) {
            Thread.sleep(random.nextInt(100));
            monitoringService.event();
        }
    }
}
