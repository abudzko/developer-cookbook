package com.budzko.cookbook.java.study.collection.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * {@link java.util.LinkedList}
 * {@link java.util.PriorityQueue}
 * {@link java.util.ArrayDeque}
 * {@link java.util.concurrent.ConcurrentLinkedDeque}
 * {@link java.util.concurrent.ConcurrentLinkedQueue}
 * {@link java.util.concurrent.SynchronousQueue}
 * {@link java.util.concurrent.LinkedBlockingQueue}
 * {@link java.util.concurrent.ArrayBlockingQueue}
 * {@link java.util.concurrent.PriorityBlockingQueue}
 * {@link java.util.concurrent.LinkedBlockingDeque}
 * {@link java.util.Collections.CheckedQueue}
 * {@link java.util.concurrent.DelayQueue}
 * {@link java.util.concurrent.LinkedTransferQueue}
 * {@link com.sun.tools.javac.util.ListBuffer}
 */
public class Queue {
    public static void main(String[] args) {
        linkedList();
    }

    private static void linkedList() {
        LinkedList<String> elements = new LinkedList<>();
        elements.add("1");
        elements.add("1");
        System.out.println(elements.size());
        elements.removeAll(List.of("1"));

        System.out.println(elements.size());
    }
}
