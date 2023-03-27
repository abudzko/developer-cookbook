package com.budzko.cookbook.algo.connectivity;

public class AlgTest {
    public static void main(String[] args) {
        int n = 10;
        ConnectivityAlg quickFindAlg = new QuickFindAlg(n);
        test(quickFindAlg);
        ConnectivityAlg quickUnionAlg = new QuickUnionAlg(n);
        test(quickUnionAlg);
        ConnectivityAlg weightedQuickUnionAlg = new WeightedQuickUnionAlg(n);
        test(weightedQuickUnionAlg);
    }

    private static void test(ConnectivityAlg alg) {
        alg.union(1, 2);
        alg.union(3, 4);
        alg.union(5, 6);
        alg.union(7, 8);
        alg.union(7, 9);
        alg.union(1, 9);
        alg.union(2, 8);

        System.out.println(alg);
        testCase(3, 2, alg);//false
        testCase(1, 7, alg);//true
        System.out.println();
    }

    private static void testCase(int p, int q, ConnectivityAlg alg) {
        System.out.printf("Connected %s - %s - %s%n", p, q, alg.connected(p, q));
    }
}
