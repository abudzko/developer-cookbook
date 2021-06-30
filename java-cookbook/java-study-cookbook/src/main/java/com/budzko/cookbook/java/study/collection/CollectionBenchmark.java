package com.budzko.cookbook.java.study.collection;

public class CollectionBenchmark {
    private static final Integer[] PARAMETERS = {10, 10, 100, 1_000, 10_000, 100_000};

    public static void main(String[] args) {
        CollectionBenchmark collectionBenchmark = new CollectionBenchmark();
        collectionBenchmark.start();

    }

    private void start() {
        for (Integer parameter : PARAMETERS) {
            add();
            insert();

        }
    }

    private void add() {

    }

    private void insert() {

    }

    private void contains() {

    }

    private void iterate() {

    }

    private void remove() {
    }


}
