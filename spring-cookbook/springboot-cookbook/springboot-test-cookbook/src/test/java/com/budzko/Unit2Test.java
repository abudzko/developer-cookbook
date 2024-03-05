package com.budzko;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Slf4j
@Tag("unit")
class Unit2Test {
    private static final int id = new Random().nextInt();
    private static String value = "init";

    @Test
    void test1() {
        value = "test1";
        print();
    }

    @Test
    void test2() {
        value = "test2";
        print();
    }

    @Test
    void test3() {
        value = "test3";
        print();
    }

    @Test
    void test4() {
        value = "test4";
        print();
    }

    @SneakyThrows
    private void print() {
        Thread.sleep(1000);
        log.info(getClass().getName() + " " + Thread.currentThread() + " " + id + " " + this.toString() + " " + value);
    }
}

