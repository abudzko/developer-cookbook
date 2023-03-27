package com.budzko.cookbook.algo.structure;

import java.util.Iterator;

public class QueueImpl {
    public static void main(String[] args) {
        QueueImp<String> queue = new QueueImp<>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.forEach(System.out::println);
        System.out.println(queue.remove());
    }

    private static class QueueImp<T> implements Iterable<T> {
        private Node<T> first;
        private Node<T> last;

        @Override
        public Iterator<T> iterator() {
            return new QueueIterator();
        }

        public void add(T value) {
            Node<T> node = new Node<>();
            node.value = value;
            if (isEmpty()) {
                first = last = node;
            } else {
                last.next = node;
                last = node;
            }
        }

        public T remove() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            T value = first.value;
            first = first.next;
            if (isEmpty()) last = null;
            return value;
        }

        public boolean isEmpty() {
            return first == null;
        }

        static class Node<T> {
            T value;
            Node<T> next;
        }

        private class QueueIterator implements Iterator<T> {
            private Node<T> current = first;

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
}
