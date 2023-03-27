package com.budzko.cookbook.algo.structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class RandomizedQueueTest {
    @Test
    void test() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(0, queue.size());
        queue.enqueue(1);
        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertEquals(1, queue.size());
        Assertions.assertEquals(1, queue.dequeue());
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(0, queue.size());

        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        Iterator<Integer> iterator1 = queue.iterator();
        Iterator<Integer> iterator2 = queue.iterator();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println();
        iterator1.forEachRemaining(System.out::println);
        System.out.println();
        iterator2.forEachRemaining(System.out::println);
    }
}
