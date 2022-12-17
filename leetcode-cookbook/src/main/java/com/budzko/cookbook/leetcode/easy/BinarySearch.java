package com.budzko.cookbook.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    private static int binarySearch(int v, int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[0] == v) {
            return 0;
        }
        int lft = 0;
        int rgt = arr.length - 1;
        while (lft <= rgt) {
            int idx = (lft + rgt) / 2;
            int currentValue = arr[idx];
            if (currentValue == v) {
                return idx;
            } else if (currentValue > v) {
                rgt = idx - 1;
            } else {
                lft = idx + 1;
            }
        }
        return -1;
    }

    //For Comparable(for example strings)
    private static <T extends Comparable<? super T>> int binarySearch(T v, T[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[0] == v) {
            return 0;
        }
        int lft = 0;
        int rgt = arr.length - 1;
        while (lft <= rgt) {
            int idx = (lft + rgt) / 2;
            T currentValue = arr[idx];
            if (isEquals(v, currentValue)) {
                return idx;
            } else if (isLesser(v, currentValue)) {
                rgt = idx - 1;
            } else {
                lft = idx + 1;
            }
        }
        return -1;
    }

    private static <T extends Comparable<? super T>> boolean isLesser(T v1, T v2) {
        return Comparator.<T>naturalOrder().compare(v1, v2) < 0;
    }

    private static <T> boolean isEquals(T t1, T t2) {
        return Objects.equals(t1, t2);
    }
}
