package com.budzko.cookbook.algo.connectivity;

public class QuickFindAlg extends ConnectivityAlg {

    //O(N)
    QuickFindAlg(int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    /**
     * O(N)
     * Join node p and q
     */
    public void union(int p, int q) {
        if (ids[p] != ids[q]) {
            int prev = ids[p];
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == prev) {
                    ids[i] = ids[q];
                }
            }
        }
    }

    /**
     * O(1)
     * Check whether p and q connected
     */
    public boolean connected(int p, int q) {
        return ids[p] == ids[q];
    }
}
