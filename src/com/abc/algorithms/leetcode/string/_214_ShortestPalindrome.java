package com.abc.algorithms.leetcode.string;

public class _214_ShortestPalindrome {
    // Check KMP
    private static String shortestPalindrome(String s) {
        int lengthOfString = s.length();

        char[] straightCharArray = s.toCharArray();

        int palindromeIdx = 0;

        for (int idx = 1; idx < lengthOfString; idx++)
            if (isPalindrome(straightCharArray, 0, idx)) palindromeIdx = idx;

        StringBuilder sb = new StringBuilder();
        for (int idx = lengthOfString - 1; idx >= palindromeIdx + 1; idx--)
            sb.append(straightCharArray[idx]);

        sb.append(s);

        return sb.toString();
    }

    private static boolean isPalindrome(char[] chars, int startIdx, int endIdx) {
        int reverseIdx = endIdx;
        for (int idx = startIdx; idx <= endIdx / 2; idx++)
            if (chars[idx] != chars[reverseIdx--]) return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(shortestPalindrome("abccbapqr"));
    }
}
