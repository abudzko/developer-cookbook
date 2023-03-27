package com.budzko.cookbook.algo.connectivity;

public class WeightedQuickUnionAlg extends ConnectivityAlg {

    int[] sizes;

    WeightedQuickUnionAlg(int n) {
        ids = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    /**
     * O(lgN)
     */
    public void union(int p, int q) {
        int rootQ = root(q);
        int rootP = root(p);
        if (rootP == rootQ) return;
        if (sizes[rootP] > sizes[rootQ]) {
            ids[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        } else {
            ids[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        }
    }

    /**
     * O(lgN)
     */
    private int root(int id) {
        while (id != ids[id]) {
            int parentId = ids[id];
            ids[id] = ids[parentId];//compress path(balance tree). Join our node to grandparent of our parent
            id = parentId;
        }
        return id;
    }

    /**
     * O(lgN)
     */
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
