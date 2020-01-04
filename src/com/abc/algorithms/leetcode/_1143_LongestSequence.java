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

    /**
     *     b a b a b
     *     0 0 0 0 0
     * a 0 0 1 1 1 1
     * b 0 1 1 2 2 2
     * b 0 1 1 2 2 3
     * b 0 1 1 2 2 3
     * b 0 1 1 2 2 3
     */

    private static int lcsFastMyWay(String str1, String str2) {
        char[] char1Array = str1.toCharArray();
        char[] char2Array = str2.toCharArray();

        int[][] result = new int[char1Array.length + 1][char2Array.length + 1];

        for (int char1Idx = 1; char1Idx <= char1Array.length; char1Idx++) {
            for (int char2Idx = 1; char2Idx <= char2Array.length; char2Idx++) {
                if (char1Array[char1Idx - 1] == char2Array[char2Idx - 1])
                    result[char1Idx][char2Idx] = result[char1Idx - 1][char2Idx - 1] + 1;
                else
                    result[char1Idx][char2Idx] = result[char1Idx][char2Idx - 1];
            }
        }

        return result[char1Array.length][char2Array.length];
    }

    public static void main(String[] args) {
        System.out.println(lcsFastMyWay("abbbba", "babab"));
    }
}
