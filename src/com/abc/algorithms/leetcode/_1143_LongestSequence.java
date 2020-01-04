package com.abc.algorithms.leetcode;

public class _1143_LongestSequence {
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

    private static String lcsFast(String str1, String str2) {
        String longer = str1.length() >= str2.length() ? str1 : str2;
        String shorter = str1.length() < str2.length() ? str1 : str2;

        String[][] memo = new String[longer.length()][shorter.length()];

        return lcsFast(longer, 0, shorter, 0, memo);
    }

    private static String lcsFast(String longer, int longerIdx, String shorter, int shorterIdx, String[][] memo) {
        if (longerIdx == longer.length() || shorterIdx == shorter.length())
            return "";

        if (memo[longerIdx][shorterIdx] != null)
            return memo[longerIdx][shorterIdx];

        if (longer.charAt(longerIdx) == shorter.charAt(shorterIdx)) {
            memo[longerIdx][shorterIdx] = longer.charAt(longerIdx) + lcsFast(longer, longerIdx + 1, shorter, shorterIdx + 1, memo);
            return memo[longerIdx][shorterIdx];
        }

        String resultA = lcsFast(longer, longerIdx + 1, shorter, shorterIdx, memo);
        String resultB = lcsFast(longer, longerIdx, shorter, shorterIdx + 1, memo);

        return resultA.length() > resultB.length() ? resultA : resultB;
    }

    public static void main(String[] args) {
        System.out.println(lcsFast("abbbb", "babab"));
    }
}
