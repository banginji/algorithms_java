package com.abc.algorithms.leetcode.uncategorized;

public class _680_Palindrome {
    private static boolean validPalindrome(String s) {
        int startIdx = 0;
        int endIdx = s.length() - 1;

        char[] chars = s.toCharArray();

        boolean removedOnce = false;

        while (startIdx < endIdx) {
            if (chars[startIdx] != chars[endIdx]) {
                if (!removedOnce) {
                    if (chars[startIdx + 1] == chars[endIdx] || chars[startIdx] == chars[endIdx - 1]) {
                        if (chars[startIdx + 1] == chars[endIdx])
                            startIdx++;
                        else
                            endIdx--;
                        removedOnce = true;
                        continue;
                    }
                    else
                        return false;
                }
                else
                    return false;
            }
            startIdx++;
            endIdx--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(validPalindrome("asdfghjkllkjhgfdsa"));
    }
}
