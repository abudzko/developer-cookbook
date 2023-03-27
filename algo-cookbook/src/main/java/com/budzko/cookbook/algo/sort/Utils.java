package com.budzko.cookbook.algo.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Utils {
    private static final Random RANDOM = new Random();

    public static List<Integer> randArray(int size, int bound) {
        return new ArrayList<>(IntStream.generate(() -> RANDOM.nextInt(bound)).limit(size).boxed().toList());
    }

    public static void checkSorted(List<Integer> randList) {
        for (int i = 0; i < randList.size() - 1; i++) {
            if (randList.get(i) > randList.get(i + 1)) {
                throw new RuntimeException("Not sorted:" + randList);
            }
        }
    }

    public static void swap(int i, int j, List<Integer> arr) {
        int tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }
}
