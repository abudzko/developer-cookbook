package com.budzko.cookbook.algo.connectivity;

public abstract class ConnectivityAlg {
    protected int[] ids;

    public abstract void union(int p, int q);

    public abstract boolean connected(int p, int q);

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getClass().getSimpleName()).append("\n");
        for (int i = 0; i < ids.length; i++) {
            result.append(i + " ");
        }
        result.append("\n");
        for (int i = 0; i < ids.length; i++) {
            result.append(ids[i] + " ");
        }
        return result.toString();
    }

}
