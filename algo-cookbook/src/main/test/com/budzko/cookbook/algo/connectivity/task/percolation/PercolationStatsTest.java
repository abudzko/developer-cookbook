package com.budzko.cookbook.algo.connectivity.task.percolation;

import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PercolationStatsTest {
    @Test
    void test2x10000() {
        PercolationStats percolationStats = new PercolationStats(2, 10000);
        printResult(percolationStats);
        Assertions.assertTrue(
                percolationStats.mean() > 0.5 && percolationStats.mean() < 0.7,
                "Was " + percolationStats.mean()
        );
        Assertions.assertTrue(
                percolationStats.stddev() > 0.009 && percolationStats.stddev() < 0.2,
                "Was " + percolationStats.stddev()
        );

        Assertions.assertTrue(
                percolationStats.confidenceLo() > 0.59 && percolationStats.confidenceLo() < 0.7,
                "Was " + percolationStats.confidenceLo()
        );

        Assertions.assertTrue(
                percolationStats.confidenceHi() > 0.59 && percolationStats.confidenceHi() < 0.7,
                "Was " + percolationStats.confidenceHi()
        );

        Assertions.assertTrue(
                percolationStats.confidenceHi() > percolationStats.confidenceLo(),
                "Was " + percolationStats.confidenceHi() + " < " + percolationStats.confidenceLo()
        );
    }

    @Test
    void test5x10000() {
        PercolationStats percolationStats = new PercolationStats(5, 10000);
        printResult(percolationStats);
        Assertions.assertTrue(
                percolationStats.mean() > 0.59 && percolationStats.mean() < 0.6,
                "Was " + percolationStats.mean()
        );
        Assertions.assertTrue(
                percolationStats.stddev() > 0.009 && percolationStats.stddev() < 0.2,
                "Was " + percolationStats.stddev()
        );

        Assertions.assertTrue(
                percolationStats.confidenceLo() > 0.59 && percolationStats.confidenceLo() < 0.6,
                "Was " + percolationStats.confidenceLo()
        );

        Assertions.assertTrue(
                percolationStats.confidenceHi() > 0.59 && percolationStats.confidenceHi() < 0.6,
                "Was " + percolationStats.confidenceHi()
        );

        Assertions.assertTrue(
                percolationStats.confidenceHi() > percolationStats.confidenceLo(),
                "Was " + percolationStats.confidenceHi() + " < " + percolationStats.confidenceLo()
        );
    }

    @Test
    void test25x10000() {
        PercolationStats percolationStats = new PercolationStats(25, 10000);
        printResult(percolationStats);
    }

    @Test
    void test200x100() {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        printResult(percolationStats);
        Assertions.assertTrue(
                percolationStats.mean() > 0.59 && percolationStats.mean() < 0.6,
                "Was " + percolationStats.mean()
        );
        Assertions.assertTrue(
                percolationStats.stddev() > 0.009 && percolationStats.stddev() < 0.015,
                "Was " + percolationStats.stddev()
        );

        Assertions.assertTrue(
                percolationStats.confidenceLo() > 0.58 && percolationStats.confidenceLo() < 0.6,
                "Was " + percolationStats.confidenceLo()
        );

        Assertions.assertTrue(
                percolationStats.confidenceHi() > 0.59 && percolationStats.confidenceHi() < 0.6,
                "Was " + percolationStats.confidenceHi()
        );

        Assertions.assertTrue(
                percolationStats.confidenceHi() > percolationStats.confidenceLo(),
                "Was " + percolationStats.confidenceHi() + " < " + percolationStats.confidenceLo()
        );

    }

    public void printResult(PercolationStats percolationStats) {
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
}
