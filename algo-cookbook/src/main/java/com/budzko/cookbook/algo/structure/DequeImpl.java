package com.budzko.cookbook.algo.structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeImpl<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public DequeImpl() {
    }

    public static void main(String[] args) {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        validate(item);
        Node<Item> node = createNode(item);
        node.next = first;
        if (isEmpty()) {
            last = node;
        } else {
            first.previous = node;
        }
        first = node;
        size++;
    }

    private void validate(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null");
        }
    }

    private Node<Item> createNode(Item item) {
        Node<Item> node = new Node<>();
        node.item = item;
        return node;
    }

    public void addLast(Item item) {
        validate(item);
        Node<Item> node = createNode(item);
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
            node.previous = last;
        }
        last = node;
        size++;
    }

    public Item removeFirst() {
        assertNotEmpty();
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        } else {
            first.previous = null;
        }
        size--;
        return item;
    }

    private void assertNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
    }

    public Item removeLast() {
        assertNotEmpty();
        Item item = last.item;
        last = last.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
