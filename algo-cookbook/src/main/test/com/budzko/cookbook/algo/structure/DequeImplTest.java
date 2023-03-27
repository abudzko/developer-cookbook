package com.budzko.cookbook.algo.structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class DequeImplTest {
    private static void assertEmpty(DequeImpl<Integer> deque) {
        Assertions.assertTrue(deque.isEmpty());
        Assertions.assertEquals(0, deque.size());
    }
    @Test
    void testAddFirst() {
        DequeImpl<Integer> deque = new DequeImpl<>();
        assertEmpty(deque);
        deque.addFirst(1);
        Assertions.assertEquals(1, deque.size());
        Assertions.assertEquals(1, deque.removeFirst());
        assertEmpty(deque);
        deque.addFirst(1);
        Assertions.assertEquals(1, deque.size());
        Assertions.assertEquals(1, deque.removeLast());
        Assertions.assertTrue(deque.isEmpty());

        deque.addFirst(1);
        deque.addFirst(2);
        Assertions.assertEquals(2, deque.size());
        Assertions.assertEquals(1, deque.removeLast());
        Assertions.assertEquals(2, deque.removeLast());
        assertEmpty(deque);

        deque.addFirst(1);
        deque.addFirst(2);
        Assertions.assertEquals(2, deque.size());
        Assertions.assertEquals(2, deque.removeFirst());
        Assertions.assertEquals(1, deque.removeFirst());
        assertEmpty(deque);
    }

    @Test
    void testAddLast() {
        DequeImpl<Integer> deque = new DequeImpl<>();
        assertEmpty(deque);
        deque.addLast(1);
        Assertions.assertEquals(1, deque.size());
        Assertions.assertEquals(1, deque.removeFirst());
        assertEmpty(deque);

        deque.addLast(1);
        Assertions.assertEquals(1, deque.size());
        Assertions.assertEquals(1, deque.removeLast());
        Assertions.assertTrue(deque.isEmpty());

        deque.addLast(1);
        deque.addLast(2);
        Assertions.assertEquals(2, deque.size());
        Assertions.assertEquals(2, deque.removeLast());
        Assertions.assertEquals(1, deque.removeLast());
        assertEmpty(deque);

        deque.addLast(1);
        deque.addLast(2);
        Assertions.assertEquals(2, deque.size());
        Assertions.assertEquals(1, deque.removeFirst());
        Assertions.assertEquals(2, deque.removeFirst());
        assertEmpty(deque);
    }

    @Test
    void testIterator() {
        DequeImpl<Integer> deque = new DequeImpl<>();
        assertEmpty(deque);
        deque.addLast(1);
        deque.addFirst(2);
        Assertions.assertEquals(2, deque.size());
        Iterator<Integer> iterator = deque.iterator();

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(2, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(1, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }
}
