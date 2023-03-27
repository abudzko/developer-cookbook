package com.budzko.cookbook.algo.structure;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items;

    public RandomizedQueue() {
        items = createArray(1);
        size = 0;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

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
        System.out.println(queue.sample());
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

    private Item[] createArray(int length) {
        return (Item[]) new Object[length];
    }

    private Item[] copyArray(Item[] source, int itemsToCopy, int newSize) {
        Item[] copy = createArray(newSize);
        for (int i = 0; i < itemsToCopy; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        validate(item);
        grow();
        items[size++] = item;
    }

    private void grow() {
        if (items.length <= size) {
            items = copyArray(items, size, 2 * size + 1);
        }
    }

    public Item dequeue() {
        assertNotEmpty();
        Item randItem = deleteRandom(items, size);
        size--;
        shrink();
        return randItem;
    }

    private Item deleteRandom(Item[] source, int itemCount) {
        int randIdx = StdRandom.uniformInt(itemCount);
        Item randItem = source[randIdx];
        int lastIdx = itemCount - 1;
        if (randIdx != lastIdx) {
            source[randIdx] = source[lastIdx];
        }
        source[lastIdx] = null;
        return randItem;
    }

    private void shrink() {
        if (items.length / 4 >= size) {
            items = copyArray(items, size, size);
        }
    }

    public Item sample() {
        assertNotEmpty();
        return items[StdRandom.uniformInt(size)];
    }

    private void validate(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null");
        }
    }

    private void assertNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] copy;
        private int remaining = size;

        RandomizedQueueIterator() {
            copy = copyArray(items, remaining, remaining);
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty");
            }
            return deleteRandom(copy, remaining--);
        }
    }
}
