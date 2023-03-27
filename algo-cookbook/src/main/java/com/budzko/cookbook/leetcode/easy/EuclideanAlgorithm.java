package com.budzko.cookbook.leetcode.easy;

public class EuclideanAlgorithm {
    public static void main(String[] args) {
        int p = 25;
        int q = 15;
        System.out.println(gcd(p, q));
    }

    public static int gcd(int p, int q) {
        return q == 0 ? p : gcd(q, p % q);
    }

}
