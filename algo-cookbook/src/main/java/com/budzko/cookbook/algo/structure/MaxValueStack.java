package com.budzko.cookbook.algo.structure;

/**
 * Stack returns max value
 * Stack with max. Create a data structure that efficiently supports the stack operations (push and pop)
 * and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.
 */
public class MaxValueStack {
    private Node head;

    public static void main(String[] args) {
        MaxValueStack maxValueStack = new MaxValueStack();
        maxValueStack.push(1);
        maxValueStack.push(3);
        maxValueStack.push(4);
        System.out.println(maxValueStack.pop());
        System.out.println(maxValueStack.pop());
        System.out.println(maxValueStack.pop());
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        int value = head.value;
        head = head.next;
        return value;
    }

    public void push(int value) {
        Node node = new Node();
        node.value = value;
        if (isEmpty()) {
            head = node;
        } else if (node.value > head.value) {
            node.next = head;
            head = node;
        } else {
            Node current = head;
            while (current.next != null && current.next.value > node.value) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static class Node {
        int value;
        Node next;
    }
}
