package com.abc.algorithms.leetcode;

public class _1143_LongestCommonSubsequence {
    private static int lcsSlow(String str1, String str2) {
        String longer = str1.length() >= str2.length() ? str1 : str2;
        String shorter = str1.length() < str2.length() ? str1 : str2;

        return lcsSlow(longer, 0, shorter, 0, 0);
    }

    private static int lcsSlow(String longer, int longerIdx, String shorter, int shorterIdx, int count) {
        if (longerIdx == longer.length() || shorterIdx == shorter.length())
            return count;

        if (longer.charAt(longerIdx) == shorter.charAt(shorterIdx))
            return lcsSlow(longer, longerIdx + 1, shorter, shorterIdx + 1, count + 1);

        return Math.max(
                lcsSlow(longer, longerIdx + 1, shorter, shorterIdx, count),
                lcsSlow(longer, longerIdx, shorter, shorterIdx + 1, count)
        );
    }

    public static void main(String[] args) {
        System.out.println(lcsSlow("abcd", "affcdeb"));
    }
}
