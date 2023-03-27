package com.budzko.cookbook.algo.structure;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private Node<T> head;

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.forEach(System.out::println);
        System.out.println(stack.pop());
    }

    void push(T value) {
        Node<T> tmp = head;
        head = new Node<>();
        head.value = value;
        head.next = tmp;
    }

    T pop() {
        T value = head.value;
        head = head.next;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    static class Node<T> {
        T value;
        Node<T> next;
    }

    private class StackIterator implements Iterator<T> {

        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.value;
            current = current.next;
            return value;
        }
    }
}
