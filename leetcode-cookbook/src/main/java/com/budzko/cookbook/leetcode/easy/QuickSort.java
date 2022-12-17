package com.budzko.cookbook.leetcode.easy;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 3, 1, 2, 4};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr) {
        int lft = 0;
        int rgt = arr.length - 1;
        sort(arr, lft, rgt);
    }

    private static void sort(int[] arr, int lft, int rgt) {
        if (lft < rgt) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int partitionIndex = partition(arr, lft, rgt);

            // Separately sort elements before
            // partition and after partition
            sort(arr, lft, partitionIndex - 1);
            sort(arr, partitionIndex + 1, rgt);
        }
    }

    private static int partition(int[] arr, int lft, int rgt) {
        int pivot = arr[rgt];
        int i = lft - 1;
        for (int j = lft; j < rgt; j++) {
            int el = arr[j];
            if (el < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, rgt);
        return (i + 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
