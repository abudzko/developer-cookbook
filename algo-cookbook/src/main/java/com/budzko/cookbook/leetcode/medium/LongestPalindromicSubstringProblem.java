package com.budzko.cookbook.leetcode.medium;

public class LongestPalindromicSubstringProblem {
    public static void main(String[] args) {
        LongestPalindromicSubstringProblem p = new LongestPalindromicSubstringProblem();
        String result = p.longestPalindrome("123321111111");
        System.out.println(result);
    }

    public String longestPalindrome(String source) {
        if (source == null || source.length() == 0) {
            return "";
        }

        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < source.length(); i++) {
            int len1 = calculatePalindromeSizeAroundCenter(source, i, i);
            int len2 = calculatePalindromeSizeAroundCenter(source, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = maxLen % 2 == 0 ? i - (maxLen - 1) / 2 : i - (maxLen) / 2;
            }

        }
        return source.substring(start, start + maxLen);
    }

    private int calculatePalindromeSizeAroundCenter(String source, int left, int right) {
        while (left >= 0 && right < source.length() && source.charAt(left) == source.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * Initial approach
     */
    public String longestPalindrome1(String source) {
        if (source == null || source.length() == 0) {
            return "";
        }
        if (source.length() == 1) {
            return source;
        }

        int size;
        int maxSize = 0;

        int start = 0;
        for (int i = 0; i < source.length(); i++) {
            size = lookForPairCount(source, i);

            if (size > maxSize) {
                maxSize = size;
                start = maxSize % 2 == 0 ? i - (maxSize - 1) / 2 : i - (maxSize - 2) / 2;

            }

        }
        return source.substring(start, start + maxSize);
    }

    private int lookForPairCount(String source, int position) {

        int result = 1;
        if (equals(source, position, position + 2)) {
            result = lookForPairCount(source, position - 1, position + 3) * 2 + 3;
        }
        if (equals(source, position, position + 1)) {
            result = Math.max(result, lookForPairCount(source, position - 1, position + 2) * 2 + 2);
        }

        return result;
    }

    private int lookForPairCount(String source, int leftPosition, int rightPosition) {
        int pairCount = 0;
        for (; leftPosition >= 0 && rightPosition < source.length(); leftPosition--, rightPosition++) {
            if (source.charAt(leftPosition) != source.charAt(rightPosition)) {
                break;
            }
            pairCount++;
        }
        return pairCount;
    }

    private boolean equals(String s, int leftPosition, int rightPosition) {
        if (leftPosition < 0 || rightPosition >= s.length()) {
            return false;
        }
        return s.charAt(leftPosition) == s.charAt(rightPosition);
    }
}
