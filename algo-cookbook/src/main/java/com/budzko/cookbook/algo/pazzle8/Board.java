package com.budzko.cookbook.algo.pazzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int n;
    private final int[][] tiles;
    private final int hamming;
    private final int manhattan;
    private int iZero;
    private int jZero;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = tiles;
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    iZero = i;
                    jZero = j;
                    break loop;
                }
            }
        }
        this.hamming = countHamming();
        this.manhattan = countManhattan();
    }

    // unit testing (not graded)
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 8, 7, 0};
//        int[] arr = {8, 1, 3, 4, 0, 2, 7, 6, 5};
        int[] arr = {1, 3, 0, 4, 2, 5, 7, 8, 6};
        int n = 3;
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            tiles[i / 3][i % 3] = arr[i];
        }
        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println(board.dimension());
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
//        board.neighbors().forEach(System.out::println);
        System.out.println(board.equals(board));
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(" ").append(tiles[i][j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    public int countHamming() {
        int counter = 0;
        for (int i = 0; i < n * n - 1; i++) {
            if (tiles[i / n][i % n] != i + 1) {
                counter++;
            }
        }
        return counter;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    public int countManhattan() {
        int sum = 0;
        for (int i = 0; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            if (row == iZero && col == jZero) {
                continue;
            }
            int value = tiles[row][col] - 1;// 8 => 8 - 1 = 7: 7 / 3 = 2, 7 % 3 = 1 => [2, 1]. So expected position of 8 is [2, 1]
            int vRow = value / n;
            int vCol = value % n;
            int distance = Math.abs(row - vRow) + Math.abs(col - vCol);
            sum += distance;
        }
        return sum;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (iZero != 2 && jZero != 2) {
            return false;
        }
        for (int i = 0; i < n * n - 1; i++) {
            int row = i / n;
            int col = i % n;
            if (tiles[row][col] != i + 1) {
                return false;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y instanceof Board that) {
            if (n != that.n) {
                return false;
            }
            if (that.iZero != iZero || that.jZero != jZero) {
                return false;
            }
            for (int i = 0; i < n * n - 1; i++) {
                int row = i / n;
                int col = i % n;
                if (tiles[row][col] != that.tiles[row][col]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void swap(int[][] tiles, int i, int j) {
        int tmp = tiles[i][j];
        tiles[i][j] = 0;
        tiles[iZero][jZero] = tmp;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        final List<Board> boards = new ArrayList<>();
        if (iZero - 1 >= 0) {
            var copy = copyTiles();
            swap(copy, iZero - 1, jZero);
            boards.add(new Board(copy));
        }
        if (iZero + 1 < n) {
            var copy = copyTiles();
            swap(copy, iZero + 1, jZero);
            boards.add(new Board(copy));
        }
        if (jZero - 1 >= 0) {
            var copy = copyTiles();
            swap(copy, iZero, jZero - 1);
            boards.add(new Board(copy));
        }
        if (jZero + 1 < n) {
            var copy = copyTiles();
            swap(copy, iZero, jZero + 1);
            boards.add(new Board(copy));
        }
        return boards;
    }

    private int[][] copyTiles() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = Arrays.copyOf(tiles[i], n);
        }
        return copy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = copyTiles();
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                int first = copy[i][j];
                int second = copy[i][j + 1];
                if (first != 0 && second != 0) {
                    copy[i][j] = second;
                    copy[i][j + 1] = first;
                    break loop;
                }
            }
        }
        return new Board(copy);
    }
}
