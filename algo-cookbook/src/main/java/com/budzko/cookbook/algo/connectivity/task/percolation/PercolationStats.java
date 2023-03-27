package com.budzko.cookbook.algo.connectivity.task.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double average;
    private final double standardDeviation;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n < 1) throw new IllegalArgumentException("Must: n > 0");
        if (trials < 1) throw new IllegalArgumentException("Must: trials > 0");
        double[] x = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            x[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
        average = StdStats.mean(x);
        standardDeviation = StdStats.stddev(x);
        confidenceLo = average - CONFIDENCE_95 * standardDeviation / Math.sqrt(trials);
        confidenceHi = average + CONFIDENCE_95 * standardDeviation / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Return: n and t expected");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println(
                String.format(
                        "95%% confidence interval = [%s, %s]",
                        percolationStats.confidenceLo(),
                        percolationStats.confidenceHi()
                )
        );
    }

    public double mean() {
        return average;
    }

    public double stddev() {
        return standardDeviation;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }
}
