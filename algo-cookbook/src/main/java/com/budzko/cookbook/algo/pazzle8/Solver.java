package com.budzko.cookbook.algo.pazzle8;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;

//https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php
public class Solver {

    private int moves = 0;
    private LinkedList<Board> solution = new LinkedList<>();

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("initial board is null");
        }
        solve(initial);
    }

    // test client (see below)
    public static void main(String[] args) {

//        int[] arr = {8, 1, 3, 4, 0, 2, 7, 6, 5};
//        int[] arr = {0, 1, 3, 4, 2, 5, 7, 8, 6};
        int[] arr = {0, 4, 1, 3, 2, 5, 7, 8, 6};//solvable?
//        int[] arr = {0, 1, 4, 3, 2, 5, 7, 8, 6};//unsolvable?
//        int[] arr = {0, 1, 3, 4, 2, 5, 7, 8, 6};//solvable
//        int[] arr = {1, 2, 3, 4, 0, 5, 7, 8, 6};
//        int[] arr = {1, 2, 3, 4, 5, 6, 8, 7, 0};
        int n = 3;
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            tiles[i / 3][i % 3] = arr[i];
        }
        Board board = new Board(tiles);
        Solver solver = new Solver(board);
        if (!solver.isSolvable())
            System.out.println("No solution possible");
        else {
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board b : solver.solution()) {
                System.out.println(b);
            }
        }
    }

    private void solve(Board board) {
        MinPQ<SearchNode> minPQ = new MinPQ<>(Comparator.comparingInt(SearchNode::getPriority));
        MinPQ<SearchNode> twinMinPQ = new MinPQ<>(Comparator.comparingInt(SearchNode::getPriority));
        SearchNode node1 = new SearchNode(board, null, 0);
        SearchNode node2 = new SearchNode(board.twin(), null, 0);
        minPQ.insert(node1);
        twinMinPQ.insert(node2);
        while (true) {
            node1 = nextNode(minPQ);
            node2 = nextNode(twinMinPQ);
            if (node1.board.isGoal()) {
                break;
            }
            if (node2.board.isGoal()) {
                break;
            }
        }
        if (node2.board.isGoal()) {
            moves = -1;
        } else {
            SearchNode node = node1;
            moves = node.move;
            while (node != null) {
                solution.push(node.board);
                node = node.prev;
            }
        }
    }

    private SearchNode nextNode(MinPQ<SearchNode> minPQ) {
        SearchNode nextNode = minPQ.delMin();
        Board board = nextNode.board;
        board.neighbors().forEach(b -> {
            if (nextNode.prev == null || !b.equals(nextNode.prev.board)) {
                minPQ.insert(new SearchNode(b, nextNode, nextNode.move + 1));
            }
        });
        return nextNode;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return moves() != -1;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in the shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return () -> solution.iterator();
    }

    private static class SearchNode {
        final int move;
        final SearchNode prev;
        final Board board;

        SearchNode(Board board, SearchNode prev, int move) {
            this.board = board;
            this.prev = prev;
            this.move = move;
        }

        int getPriority() {
            return board.manhattan() + move;
        }
    }
}
