package com.budzko.cookbook.leetcode.easy;

public class EuclideanAlgorithm {
    public static void main(String[] args) {
        int a = 25;
        int b = 15;
        System.out.println(find(a, b));
    }

    private static int find(int a, int b) {
        if (a == 0) {
            return b;
        }
        return find(b % a, a);
    }
}
