package com.abc.algorithms.leetcode.string;

public class _1332_RemovePalindromeSub {
    private static int removePalindromeSub(String s) {
        if (s.length() == 0) return 0;
        if (isPalindrome(s)) return 1;
        return 2;
    }

    private static boolean isPalindrome(String s) {
        char[] c = s.toCharArray();
        int ptr = c.length - 1;
        for (int idx = 0; idx <= c.length / 2 - 1; idx++)
            if (c[idx] != c[ptr--]) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(removePalindromeSub("aabbaa"));
    }
}
