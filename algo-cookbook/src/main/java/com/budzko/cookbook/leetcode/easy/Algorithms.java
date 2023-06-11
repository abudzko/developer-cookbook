package com.budzko.cookbook.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Algorithms {
    public static void main(String[] args) {

    }
}

class SolutionQuickSort {
    void quickSort(int[] arr) {
        quickSort(0, arr.length - 1, arr);
    }

    void quickSort(int l, int r, int[] arr) {
        if (l >= r) {
            return;
        }
        int pIdx = partition(l, r, arr);
        quickSort(l, pIdx - 1, arr);
        quickSort(pIdx + 1, r, arr);
    }

    private int partition(int l, int r, int[] arr) {
        int pv = arr[r];
        int idx = l;
        while (l < r) {
            if (arr[l] < pv) {
                swap(l, idx, arr);
                idx++;
            }
            l++;
        }
        swap(idx, r, arr);
        return idx;
    }

    private void swap(int l, int r, int[] arr) {
        if (l == r) {
            return;
        }
        int v = arr[l];
        arr[l] = arr[r];
        arr[r] = v;
    }
}

class SolutionHasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode first = head;
        ListNode second = head;
        while (first != null && first.next != null) {
            first = first.next.next;
            second = second.next;

            if (first == second) {
                return true;
            }
        }
        return false;

    }
}

class SolutionReorderList {

    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = null;
        ListNode h = slow.next;
        slow.next = null;
        ListNode reversed = null;
        while (h != null) {
            next = h.next;
            h.next = reversed;
            reversed = h;
            h = next;
        }
        ListNode tmp1 = null;
        ListNode tmp2 = null;
//        while (reversed != null) {
//            tmp1 = head.next;
//            tmp2 = reversed.next;
//            head.next = reversed;
//            reversed.next = tmp1;
//            head = tmp1;
//            reversed = tmp2;
//        }
        while (reversed != null) {
            tmp1 = head.next;
            tmp2 = reversed.next;
            head.next = reversed;
            reversed.next = tmp1;
            head = tmp1;
            reversed = tmp2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

class SolutionMedSorted {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] a = nums1;
        int[] b = nums2;
        if (a.length > b.length) {
            int[] tmp = a;
            a = b;
            b = tmp;
        }
        int len1 = a.length;
        int len2 = b.length;
        int len = len1 + len2;
        int half = len / 2;
        int l = 0;
        int r = len1 - 1;
        while (true) {
            int i = l + r < 0 ? -1 : (l + r) / 2;
            int j = half - i - 2;
            int aLeft = i >= 0 ? a[i] : Integer.MIN_VALUE;
            int aRight = i + 1 < len1 ? a[i + 1] : Integer.MAX_VALUE;
            int bLeft = j >= 0 ? b[j] : Integer.MIN_VALUE;
            int bRight = j + 1 < len2 ? b[j + 1] : Integer.MAX_VALUE;
            if (aLeft <= bRight && bLeft <= aRight) {
                if (len % 2 == 1) {
                    return Math.min(aRight, bRight);
                }
                return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / (double) 2;
            } else if (aLeft > bRight) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
    }
}

class MinMaxStack {

    private final Deque<Integer> stack;
    private final Deque<Integer> minStack;
    private final Deque<Integer> maxStack;

    public MinMaxStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        maxStack = new LinkedList<>();
    }

    public void push(int val) {
        stack.push(val);
        int min = minStack.isEmpty() ? val : Math.min(getMin(), val);
        minStack.push(min);
        int max = maxStack.isEmpty() ? val : Math.max(getMax(), val);
        maxStack.push(max);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
        maxStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public int getMax() {
        return maxStack.peek();
    }
}

class SolutionMinWin {
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return "";
        }
        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < tLen; i++) {
            char ch = t.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < tLen - 1; i++) {
            char ch = s.charAt(i);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
        }
        int l = 0;
        int r = tLen - 1;
        int pL = 0;
        int pR = 0;
        int minL = Integer.MAX_VALUE;
        while (r < sLen) {
            char ch = s.charAt(r);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
            while (include(sMap, tMap)) {
                int len = r - l + 1;
                if (len < minL) {
                    minL = len;
                    pL = l;
                    pR = r + 1;
                }
                char lCh = s.charAt(l);
                sMap.put(lCh, sMap.getOrDefault(lCh, 0) - 1);
                l++;
            }
            r++;

        }

        return s.substring(pL, pR);
    }

    private boolean include(Map<Character, Integer> source, Map<Character, Integer> sub) {
        for (Map.Entry<Character, Integer> e : sub.entrySet()) {
            Character ch = e.getKey();
            Integer count = e.getValue();
            Integer count2 = source.get(ch);
            if (count2 == null || count > count2) {
                return false;
            }
        }
        return true;
    }
}

class SolutionThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int target = 0;
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    List<Integer> values = new ArrayList<>();
                    values.add(nums[i]);
                    values.add(nums[l]);
                    values.add(nums[r]);
                    set.add(values);
                    l++;
                    r--;
                } else if (sum > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return new ArrayList<>(set);
    }
}

class SolutionIsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char ch = board[i][j];
                if (ch == '.') {
                    continue;
                }
                String s = String.valueOf(ch);
                if (!set.add(s + i)
                        || !set.add(s + j)
                        || !set.add(s + i / 3 + j / 3)) {
                    return false;
                }

            }
        }
        return true;
    }
}

class SolutionWaysToSplit {
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        long[] sums = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sums[i] += sum;
        }
        int count = countSplit(nums, sums, 0, 1, 0);
        return count;
    }

    private int countSplit(int[] nums, long[] sums, int start, int step, long prevSum) {
        if (step >= 3) {
            return 1;
        }
        int n = nums.length;
        int result = 0;
        long totalSum = sums[n - 1];
        for (int i = start; i < n; i++) {
            long l = sums[i] - prevSum;
            long rSum = totalSum - sums[i];
            if (rSum < l) {
                break;
            }
            if (l >= prevSum) {
                result += countSplit(nums, sums, i + 1, step + 1, l);
            }
        }
        return result;
    }
}

class SolutionDistance {
    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int idx = i;
            map.compute(
                    num,
                    (v, list) -> {
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        list.add(idx);
                        return list;
                    }
            );
        }
        long[] result = new long[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            List<Integer> list = map.get(num);
            long sum = 0;
            for (int idx : list) {
                if (idx != i) {
                    sum += Math.abs(i - idx);
                }
            }
            result[i] = sum;
        }
        return result;
    }
}

class SolutionMinSubArr {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long[] l = new long[n];
        long[] r = new long[n];
        long lSum = 0;
        long rSum = 0;
        for (int i = 0; i < n; i++) {
            lSum += nums[i];
            l[i] = lSum;
            rSum += nums[n - 1 - i];
            r[n - 1 - i] = rSum;

        }

        int lCount = 0;
        int rCount = 0;
        for (int i = 0; i < n; i++) {
            if (l[i] % p == 0) {
                lCount = i + 1;
            }
            if (r[i] % p == 0) {
                rCount = i + 1;
            }
        }
        int result = Math.min(n - lCount, n - rCount);
        return result == n ? -1 : result;
    }
}


class SolutionCanReach2 {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int maxIdx = 0;
        while (!queue.isEmpty()) {
            Integer idx = queue.remove();
            if (idx == n - 1) {
                return true;
            }
            for (int i = Math.max(idx + minJump, maxIdx); i <= Math.min(idx + maxJump, n - 1); i++) {
                if (s.charAt(i) == '0') {
                    queue.add(i);
                }
            }
            maxIdx = Math.min(idx + maxJump + 1, n - 1);
        }
        return false;
    }

}

class SolutionCanReach {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }
        int[] indexes = new int[n];
        int lastZeroIndx = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                lastZeroIndx = i;
            }
            indexes[i] = lastZeroIndx;
        }
        return can(s, n, 0, minJump, maxJump, indexes);
    }

    private boolean can(String s, int n, int start, int minJump, int maxJump, int[] indexes) {
        if (start == n - 1) {
            return true;
        }
        int l = start + minJump;
        if (l > n - 1) {
            return false;
        }
        int r = Math.min(start + maxJump, n - 1);
        int i = indexes[r];
        while (i > l) {
            if (can(s, n, i, minJump, maxJump, indexes)) {
                return true;
            }
            if (r - i > maxJump - minJump) {
                break;
            }
            i = indexes[i - 1];
        }
        return false;
    }
}

class SolutionSunArrDivByK {
    public int subarraysDivByK(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            int key = sum % k;
//            key = key > 0 ? key : -key;// k is added to handle negative remainders [-4 % 9 = -5 and (-5 + 9) % 9 = 4]
            Integer currentCount = map.getOrDefault(key, 0);
            count += currentCount;
            map.put(key, currentCount + 1);
        }
        return count;
    }
}


class Solution3 {

    private final double[] sums;

    public Solution3(int[] w) {
        double totalSum = 0;
        for (int i = 0; i < w.length; i++) {
            totalSum += w[i];
        }
        sums = new double[w.length];
        double tmpSum = 0;
        for (int i = 0; i < w.length; i++) {
            int v = w[i];
            tmpSum += v;
            sums[i] = tmpSum / totalSum;
        }
    }

    public int pickIndex() {
        double value = 0;//Math.random();
        int l = 0;
        int r = sums.length - 1;
        int m;
        while (l < r) {
            m = (l + r) / 2;
            double mValue = sums[m];
            if (mValue == value) {
                break;
            } else if (mValue > value) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}

class SolutionLargestSumOfAv {
    public static double largestSumOfAverages(int[] nums, int k) {
        Double[][] c = new Double[nums.length][k];
        return solve(nums, 0, k, c);
    }

    private static double solve(int[] nums, int start, int k, Double[][] c) {
        if (start >= nums.length || k < 0) {
            return 0;
        }
        if (k == 0) {
            return Integer.MIN_VALUE;
        }
        if (c[start][k] != null) {
            return c[start][k];

        }
        double result = 0.0;
        double sum = 0.0;
        for (int i = start; i < nums.length; i++) {
            sum += nums[i];
            result = Math.max(result, sum / (i - start + 1) + solve(nums, i + 1, k - 1, c));
        }
        c[start][k] = result;
        return result;
    }
}

class Solution88 {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        map.put(0, 1);
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            int diff = sum - k;
            if (map.containsKey(diff)) {
                ans += map.get(diff);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}

class SolutionSunArrSum {
    public boolean checkSubarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum % k)) {
                map.put(sum % k, i + 1);
            } else {
                if (map.get(sum % k) < i) {
                    return true;
                }
            }
        }
        return false;
    }
}

class NumMatrix {
    Map<String, Integer> map = new HashMap<>();

    public NumMatrix(int[][] matrix) {
        build(matrix, 0, 0);
    }

    private void build(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (row >= n || col >= m) {
            return;
        }
        int i = row;
        while (i < n) {
            int j = col;
            while (j < m) {
                map.put("" + row + col + i + j, count(matrix, row, col, i, j));
                j++;
            }
            i++;
        }
        build(matrix, row + 1, col);
        build(matrix, row, col + 1);
    }

    private int count(int[][] matrix, int row1, int col1, int row2, int col2) {
        int sum = 0;
        while (row1 <= row2) {
            int j = col1;
            while (j <= col2) {
                sum += matrix[row1][j];
                j++;
            }
            row1++;
        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return map.get("" + row1 + col1 + row2 + col2);
    }
}

class SolutionMinSumArr {

    public int minSubArrayLen(int target, int[] nums) {
        int i = -1, j = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        int c = 0;
        while (i < nums.length) {
            if (sum >= target) {
                ans = Math.min(ans, c);
                sum -= nums[j];
                j++;
                c--;
            } else {
                i++;
                if (i < nums.length) {
                    sum += nums[i];
                    c++;
                }
            }
        }
        ans = ans == Integer.MAX_VALUE ? 0 : ans;
        return ans;
    }
}

class SolutionMinSubAr {
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int result = Integer.MAX_VALUE;
        int counter = 0;
        int sum = 0;
        while (i <= nums.length) {
            if (sum >= target) {
                result = Math.min(result, counter);
                sum -= nums[j];
                j++;
                counter--;
            } else {
                if (i < nums.length) {
                    sum += nums[i];
                    counter++;
                }
                i++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

class Solution4Sum2 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Map<Long, List<Integer[]>> sum12 = new HashMap<>();
        TreeSet<Integer[]> unique = new TreeSet<>((ar1, ar2) -> {
            int v1 = nums[ar1[0]];
            int v2 = nums[ar1[1]];
            int v3 = nums[ar2[0]];
            int v4 = nums[ar2[1]];
            return v1 == v3 && v2 == v4 ? 0 : v1 > v3 ? 1 : v2 > v4 ? 1 : -1;
        });
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long sum = (long) nums[i] + nums[j];
                List<Integer[]> indexList = sum12.computeIfAbsent(sum, k -> new ArrayList<>());
                Integer[] indexes = new Integer[2];
                indexes[0] = i;
                indexes[1] = j;
                if (unique.add(indexes)) {
                    indexList.add(indexes);
                }
            }
        }

        Set<List<Integer>> tmpSet = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long sum = (long) nums[i] + nums[j];
                List<Integer[]> indexList = sum12.get(target - sum);
                if (indexList != null) {
                    for (Integer[] indexes : indexList) {
                        if (indexes[1] < i) {
                            ArrayList<Integer> values = new ArrayList<>(4);
                            values.add(nums[indexes[0]]);
                            values.add(nums[indexes[1]]);
                            values.add(nums[i]);
                            values.add(nums[j]);
                            Collections.sort(values);
                            tmpSet.add(values);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(tmpSet);
    }
}

class Solution4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int l = j + 1;
                int r = nums.length - 1;
                long twoSum = nums[i] + nums[j];
                while (l < r) {
                    long sum = twoSum + nums[l] + nums[r];
                    if (sum == target) {
                        List<Integer> values = new ArrayList<>(4);
                        values.add(nums[i]);
                        values.add(nums[j]);
                        values.add(nums[l]);
                        values.add(nums[r]);
                        set.add(values);
                        l++;
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class SolutionNth {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = head;
        LinkedList<ListNode> stack = new LinkedList<>();
        do {
            stack.add(tail);
            tail = tail.next;
        } while (tail != null);
        ListNode node = null;
        while (n > 0) {
            node = stack.removeLast();
            n--;
        }
        if (stack.isEmpty()) {
            return null;
        } else {
            ListNode previousNode = stack.removeLast();
            previousNode.next = node.next;
            return head;
        }
    }
}

class SolutionParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(n, n, result, "");
        return result;
    }

    private void generate(int left, int right, List<String> result, String tmp) {
        if (right < left) {
            return;
        }
        if (left < 0) {
            return;
        }
        if (right == 0) {
            result.add(tmp);
            return;
        }
        generate(left - 1, right, result, tmp + "(");
        generate(left, right - 1, result, tmp + ")");
    }
}