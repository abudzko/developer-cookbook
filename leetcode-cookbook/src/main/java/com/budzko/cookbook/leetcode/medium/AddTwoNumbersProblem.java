package com.budzko.cookbook.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbersProblem {
    public static void main(String[] args) {
        String v1 = "243";
        String v2 = "564";
        v1 = "9999999";
        v2 = "9999";
        v1 = "0";
        v2 = "0";
        ListNode l1 = createListNode(v1);
        ListNode l2 = createListNode(v2);

        ListNode result = new AddTwoNumbersProblem().addTwoNumbers(l1, l2);
        System.out.println(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return calculate(l1, l2, 0);
    }

    private ListNode calculate(ListNode l1, ListNode l2, int extra) {
        if (l1 == null && l2 == null) {
            if (extra > 0) {
                return new ListNode(extra);
            } else {
                return null;
            }
        }
        int sum;
        if (l1 != null && l2 != null) {
            sum = l1.val + l2.val;
            l1 = l1.next;
            l2 = l2.next;
        } else if (l1 != null) {
            sum = l1.val;
            l1 = l1.next;
        } else {// l2 != null
            sum = l2.val;
            l2 = l2.next;
        }

        sum = sum + extra;
        if (sum > 9) {
            extra = 1;
            sum = sum % 10;
        } else {
            extra = 0;
        }

        return new ListNode(sum, calculate(l1, l2, extra));
    }

    private static ListNode createListNode(String value) {
        return build(value, 0);
    }

    private static ListNode build(String source, int index) {
        if (index < source.length()) {
            return new ListNode(Character.getNumericValue(source.charAt(index)), build(source, index + 1));
        }
        return null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode node = this;
        List<String> result = new ArrayList<>();
        while (node != null) {
            result.add(String.valueOf(node.val));
            node = node.next;
        }
        return result.toString();

    }
}