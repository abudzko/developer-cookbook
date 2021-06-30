package com.budzko.cookbook.java.study.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class CollectionStudy {
    public static void main(String[] args) {
        shuffle();
        retainAll();
        priorityQueue();
        iterable();
    }

    private static void iterable() {
        class CustomContainer<T> implements Iterable<T> {
            private List<T> elements = new ArrayList<>();

            public void add(T element) {
                elements.add(element);
            }

            @Override
            public Iterator<T> iterator() {
                return new Iterator<>() {
                    private int counter = 0;

                    @Override
                    public boolean hasNext() {
                        return counter < elements.size();
                    }

                    @Override
                    public T next() {
                        return elements.get(counter++);
                    }
                };
            }
        }

        CustomContainer<String> strings = new CustomContainer<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        for (String s : strings) {//CustomContainer implements Iterable
            System.out.println(s);
        }
    }

    private static void priorityQueue() {
        PriorityQueue<String> strings = new PriorityQueue<>(Collections.reverseOrder());
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");

        String v;
        while ((v = strings.poll()) != null) {
            System.out.println(v);
        }

        class A {
        }

        try {
            PriorityQueue<A> queue = new PriorityQueue<>();
            queue.add(new A());
        } catch (ClassCastException e) {
            e.printStackTrace(); //ClassCastException: cannot be cast to class java.lang.Comparable
        }
    }

    private static void shuffle() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        Collections.shuffle(strings);
        System.out.println(strings);
    }

    private static void retainAll() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        strings.retainAll(List.of("1"));
        System.out.println(strings);
    }
}
