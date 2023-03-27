package com.budzko.cookbook.algo.connectivity.task.percolation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PercolationTest {
    @Test
    void test1x1() {
        Percolation percolation = new Percolation(1);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertFalse(percolation.isFull(1, 1));
        percolation.open(1, 1);
        Assertions.assertTrue(percolation.percolates());
        Assertions.assertTrue(percolation.isFull(1, 1));
    }

    @Test
    void test2x2() {
        Percolation percolation = new Percolation(2);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertFalse(percolation.isFull(1, 1));
        Assertions.assertFalse(percolation.isFull(1, 2));
        percolation.open(1, 2);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertTrue(percolation.isFull(1, 2));

        percolation.open(2, 2);
        Assertions.assertTrue(percolation.percolates());
        Assertions.assertTrue(percolation.isFull(2, 2));
    }

    @Test
    void test3x3() {
        Percolation percolation = new Percolation(3);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertFalse(percolation.isFull(1, 1));
        Assertions.assertFalse(percolation.isFull(1, 2));
        Assertions.assertFalse(percolation.isFull(1, 3));

        percolation.open(1, 1);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertTrue(percolation.isFull(1, 1));

        percolation.open(2, 1);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertTrue(percolation.isFull(2, 1));

        percolation.open(2, 3);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertFalse(percolation.isFull(2, 3));

        percolation.open(2, 2);
        Assertions.assertFalse(percolation.percolates());
        Assertions.assertTrue(percolation.isFull(2, 2));
        Assertions.assertTrue(percolation.isFull(2, 3));

        percolation.open(3, 3);
        Assertions.assertTrue(percolation.percolates());
        Assertions.assertTrue(percolation.isFull(3, 3));
        Assertions.assertTrue(percolation.isFull(2, 3));
    }

    @Test
    void testOpenOnce() {
        Percolation percolation = new Percolation(3);
        percolation.open(2, 2);
        Assertions.assertFalse(percolation.isFull(2, 2));
        Assertions.assertTrue(percolation.isOpen(2, 2));
        Assertions.assertEquals(1, percolation.numberOfOpenSites());
        percolation.open(2, 2);
        Assertions.assertEquals(1, percolation.numberOfOpenSites());
        Assertions.assertTrue(percolation.isOpen(2, 2));

    }
}
