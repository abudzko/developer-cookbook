package com.budzko.cookbook.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreeSumArrayAlg {

    protected static final Random RANDOM = new Random();

    public static void main(String[] args) {
        ThreeSumArrayAlg threeSumArrayAlg = new ThreeSumArrayAlg();
//        int n = 3;
//        int[] arr1 = genArray(n);
//        int[] arr2 = genArray(n);
//        int[] arr3 = genArray(n);
//        System.out.println(threeSumArrayAlg.threeSum(arr1, arr2, arr3));
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.toString(arr2));
//        System.out.println(Arrays.toString(arr3));
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        int[] arr3 = {5, 6, 7};
        System.out.println(threeSumArrayAlg.threeSum(arr1, arr2, arr3));
    }

    private int threeSum(int[] arr1, int[] arr2, int[] arr3) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int k : arr1) {
            for (int l : arr2) {
                map.put(k + l, map.getOrDefault(k + l, 0) + 1);
            }
        }
        int count = 0;
        for (int i : arr3) {
            count += map.getOrDefault(-i, 0);
        }
        return count;
    }

    private static int[] genArray(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int number = RANDOM.nextInt(100);
            if (RANDOM.nextBoolean()) {
                number = -number;
            }
            result[i] = number;
        }
        return result;
    }
}
