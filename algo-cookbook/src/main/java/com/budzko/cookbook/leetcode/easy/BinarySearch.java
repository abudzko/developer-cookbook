package com.budzko.cookbook.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class BinarySearch {
    public static void main(String[] args) {

        int size = 10;
        int[] arr = IntStream.iterate(1, i -> i * 2).limit(size).boxed().mapToInt(i -> i).toArray();
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(256, arr));


        List<String> data = new ArrayList<>(List.of("b", "c", "aa", "ac", "x", "bc"));
        data.sort(Comparator.naturalOrder());
        System.out.println(data);
        System.out.println(binarySearch("x", data.toArray(String[]::new)));
    }

    private static int binarySearch(int value, int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int lft = 0;
        int rgt = arr.length - 1;
        while (lft <= rgt) {
            int idx = (lft + rgt) / 2;
            int currentValue = arr[idx];
            if (value < currentValue) {
                rgt = idx - 1;
            } else if (value > currentValue) {
                lft = idx + 1;
            } else {
                return idx;
            }
        }
        return -1;
    }

    //For Comparable(for example strings)
    private static <T extends Comparable<? super T>> int binarySearch(T value, T[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int lft = 0;
        int rgt = arr.length - 1;
        while (lft <= rgt) {
            int idx = (lft + rgt) / 2;
            T currentValue = arr[idx];
            if (isValueLesser(value, currentValue)) {
                rgt = idx - 1;
            } else if (isValueGreater(value, currentValue)) {
                lft = idx + 1;
            } else {
                return idx;
            }
        }
        return -1;
    }

    private static <T extends Comparable<? super T>> boolean isValueLesser(T theValue, T compareWith) {
        return Comparator.<T>naturalOrder().compare(theValue, compareWith) < 0;
    }

    private static <T extends Comparable<? super T>> boolean isValueGreater(T theValue, T compareWith) {
        return Comparator.<T>naturalOrder().compare(theValue, compareWith) > 0;
    }

}
