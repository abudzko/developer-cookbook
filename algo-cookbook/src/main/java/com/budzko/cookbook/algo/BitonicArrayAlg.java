package com.budzko.cookbook.algo;

/**
 * Peak Index in a Mountain Array
 * Bionic Array - max value
 */
public class BitonicArrayAlg {
    public static void main(String[] args) {
        int[] arr1 = {-10, -7, -6, -1, 11, 8, 6, 3, -2};
        int[] arr2 = {0, 3, 10, 9, 8, 4, 2, 1};
        System.out.println(arr1[maxIdx(arr1)]);
        System.out.println(arr2[maxIdx(arr2)]);

        System.out.println(findIdx(2, arr2));
    }

    private static int maxIdx(int[] arr) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Must: size >= 3 ");
        }
        int lf = 0;
        int rgt = arr.length;
        int midIdx;
        while (lf <= rgt) {
            midIdx = lf + (rgt - lf) / 2;
            if (arr[midIdx] < arr[midIdx + 1]) {
                lf = midIdx + 1;
            } else {
                rgt = midIdx - 1;
            }
        }
        return lf;
    }

    private static int findIdx(int value, int[] arr) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Must: size >= 3 ");
        }
        int maxIdx = maxIdx(arr);
        int idx = findIdx(0, maxIdx, value, arr, 1);
        if (idx == -1) {
            idx = findIdx(maxIdx, arr.length, value, arr, -1);
        }
        return idx;
    }

    private static int findIdx(int start, int end, int value, int[] arr, int order) {
        int midIdx;
        value = value * order;
        while (start <= end) {
            midIdx = start + (end - start) / 2;
            int midValue = arr[midIdx] * order;
            if (midValue < value) {
                start = midIdx + 1;
            } else if (midValue > value) {
                end = midIdx - 1;
            } else {
                return midIdx;
            }
        }
        return -1;
    }
}
