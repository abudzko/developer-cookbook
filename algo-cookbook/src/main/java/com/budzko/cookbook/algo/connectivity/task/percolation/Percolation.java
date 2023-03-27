package com.budzko.cookbook.algo.connectivity.task.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final boolean[][] sites;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final int n;
    private final int virtualTopNode;
    private final int virtualBottomNode;
    private int openSitesCount = 0;

    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("Must: n > 0 but was " + n);
        this.sites = new boolean[n][n];
        this.n = n;
        int elementCount = n * n;
        //virtual top + virtual bottom = 2
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(elementCount + 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = false;
            }
        }
        this.virtualTopNode = elementCount;
        this.virtualBottomNode = elementCount + 1;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(3, 3);
        System.out.println(percolation.isFull(3, 3));
        System.out.println(percolation.percolates());
    }

    private int toNode(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (isInBounds(row) && isInBounds(col)) {
            if (!isOpen(row, col)) {
                int x = toX(row);
                int y = toY(col);
                sites[x][y] = true;
                openSitesCount++;
                unionNeighbors(row, col);
            }
        }
    }

    private void unionNeighbors(int row, int col) {
        union(row, col, row - 1, col);
        union(row, col, row + 1, col);
        union(row, col, row, col - 1);
        union(row, col, row, col + 1);
    }

    private void union(
            int raw,
            int col,
            int neighborRow,
            int neighborCol
    ) {
        if (isInBounds(neighborRow) && isInBounds(neighborCol)) {
            if (isOpen(neighborRow, neighborCol)) {
                weightedQuickUnionUF.union(toNode(raw, col), toNode(neighborRow, neighborCol));
            }
        } else {
            if (neighborRow == 0) {
                weightedQuickUnionUF.union(toNode(raw, col), virtualTopNode);
            }
            if (neighborRow == n + 1) {
                weightedQuickUnionUF.union(toNode(raw, col), virtualBottomNode);
            }
        }
    }

    private int toX(int row) {
        return row - 1;
    }

    private int toY(int col) {
        return col - 1;
    }

    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return sites[toX(row)][toY(col)];
    }

    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        return weightedQuickUnionUF.find(virtualTopNode) == weightedQuickUnionUF.find(toNode(row, col));
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.find(virtualTopNode) == weightedQuickUnionUF.find(virtualBottomNode);
    }

    /**
     * @param position row or column
     */
    private void validate(int position) {
        if (position <= 0 || position > n) {
            throw new IllegalArgumentException("Must: 0 < row/col <= n but was " + position);
        }
    }

    /**
     * @param position row or column
     */
    private boolean isInBounds(int position) {
        return position > 0 && position <= n;
    }
}
