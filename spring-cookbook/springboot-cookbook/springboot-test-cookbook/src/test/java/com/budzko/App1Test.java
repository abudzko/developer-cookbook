package com.budzko;

import com.budzko.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@Tag("boot")
class App1Test {

    @Autowired
    private AppService appService;

    @Test
    void test1() {
        appService.print();
    }

    @Test
    void test2() {
        appService.print();
    }
}
