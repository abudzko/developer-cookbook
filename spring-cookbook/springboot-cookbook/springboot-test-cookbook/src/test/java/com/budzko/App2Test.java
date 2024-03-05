package com.budzko;

import com.budzko.service.AppService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("boot")
class App2Test {

    @Autowired
    private AppService appService;

    @Test
    void test3() {
        appService.print();
    }

    @Test
    void test4() {
        appService.print();
    }
}
