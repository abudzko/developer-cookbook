package com.budzko.cookbook.algo.connectivity;

public class QuickUnionAlg extends ConnectivityAlg {

    QuickUnionAlg(int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootQ = root(q);
        int rootP = root(p);
        if (rootP == rootQ) return;
        ids[rootQ] = rootP;
    }

    private int root(int id) {
        while (id != ids[id]) {
            id = ids[id];
        }
        return id;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
