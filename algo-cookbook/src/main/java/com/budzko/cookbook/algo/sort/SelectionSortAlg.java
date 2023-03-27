package com.budzko.cookbook.algo.sort;

import java.util.List;

import static com.budzko.cookbook.algo.sort.Utils.checkSorted;
import static com.budzko.cookbook.algo.sort.Utils.swap;

public class SelectionSortAlg {
    public static void main(String[] args) {
        List<Integer> arr = Utils.randArray(20, 100);
        System.out.println(arr);
        sort(arr);
        checkSorted(arr);
        System.out.println(arr);
    }

    private static void sort(List<Integer> arr) {
        if (arr == null || arr.size() < 2) {
            return;
        }

        for (int i = 0; i < arr.size() - 1; i++) {
            int minIdx = findMin(i, arr);
            swap(i, minIdx, arr);
        }
    }

    private static int findMin(int i, List<Integer> arr) {
        int minIdx = i;
        for (int j = i + 1; j < arr.size(); j++) {
            if (arr.get(j) < arr.get(minIdx)) {
                minIdx = j;
            }
        }
        return minIdx;
    }
}
