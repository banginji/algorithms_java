package com.abc.algorithms.leetcode;

public class _1143_LongestCommonSubsequence {
    private static int lcsSlow(String str1, String str2) {
        String longer = str1.length() >= str2.length() ? str1 : str2;
        String shorter = str1.length() < str2.length() ? str1 : str2;

        return lcsSlow(longer, 0, shorter, 0, 0);
    }

    private static int lcsSlow(String str1, int str1Idx, String str2, int str2Idx, int count) {
        if (str1Idx == str1.length() || str2Idx == str2.length())
            return count;

        if (str1.charAt(str1Idx) == str2.charAt(str2Idx))
            return lcsSlow(str1, ++str1Idx, str2, ++str2Idx, ++count);

        return Math.max(
                lcsSlow(str1, ++str1Idx, str2, str2Idx, count),
                lcsSlow(str1, str1Idx, str2, ++str2Idx, count)
        );
    }

    public static void main(String[] args) {
        System.out.println(lcsSlow("abcd", "affcdeb"));
    }
}
