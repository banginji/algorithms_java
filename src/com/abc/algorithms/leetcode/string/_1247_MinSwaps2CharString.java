package com.abc.algorithms.leetcode.string;

public class _1247_MinSwaps2CharString {
    private static int numberOfMinSwaps(String s1, String s2) {
        char[] ca1 = s1.toCharArray();
        char[] ca2 = s2.toCharArray();

        int xY = 0, yX = 0;

        for (int idx = 0; idx < ca1.length; idx++) {
            if (ca1[idx] == ca2[idx]) continue;
            if (ca1[idx] == 'x') xY++; else yX++;
        }

        if ((xY + yX) % 2 == 1) return -1;
        return xY / 2 + yX / 2 + (xY % 2 == 1 ? 2 : 0);
    }

    // xxyy xyyx
    public static void main(String[] args) {
        System.out.println(numberOfMinSwaps("xxyy", "xyyx"));
    }
}
