package com.budzko.cookbook.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharactersProblem {


    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharactersProblem p = new LongestSubstringWithoutRepeatingCharactersProblem();
        int result = p.lengthOfLongestSubstring3("pwwkewav");
        System.out.println(result);
    }

    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int result = 0;
        HashMap<Character, Integer> tmp = new HashMap<>();
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (tmp.containsKey(ch)) {
                Integer previousPosition = tmp.get(ch);
                start = Math.max(start, previousPosition);
            }
            result = Math.max(result, end - start + 1);
            tmp.put(ch, ++end);
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int result = 0;
        HashSet<Character> reusable = new HashSet<>();
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (reusable.add(ch)) {
                end++;
            } else {
                result = Math.max(result, end - start);
                start++;
                end = start;
                reusable.clear();
            }
        }
        result = Math.max(result, end - start);
        return result;
    }

    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int result = 0;
        HashMap<Character, Integer> reusable = new HashMap<>();
        while (end < s.length()) {
            char ch = s.charAt(end);
            Integer previous = reusable.put(ch, end);
            if (previous == null) {
                end++;
            } else {
                result = Math.max(result, end - start);
                start = previous + 1;
                end = start;
                reusable.clear();
            }
        }
        result = Math.max(result, end - start);
        return result;
    }

}
