package com.budzko.cookbook.algo.sort;

import java.util.List;

public class InsertionSortAlg {
    public static void main(String[] args) {
        List<Integer> arr = Utils.randArray(20, 100);
        System.out.println(arr);
        sort(arr);
        Utils.checkSorted(arr);
        System.out.println(arr);
    }

    private static void sort(List<Integer> arr) {
        if (arr == null || arr.size() < 2) {
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (arr.get(j) < arr.get(j - 1)) {
                    Utils.swap(j, j - 1, arr);
                } else break;
            }
        }
    }
}
