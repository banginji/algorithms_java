package com.abc.algorithms.leetcode.array;

public class _1208_SubStringByBudget {
    private static int equalSubString(String s, String t, int maxCost) {
        int[] cost = new int[s.length()];
        char[] sca = s.toCharArray(), tca = t.toCharArray();
        for (int idx = 0; idx < cost.length; idx++) cost[idx] = Math.abs((sca[idx] - 'a') - (tca[idx] - 'a'));
        int leftIdx = 0, rightIdx = 0;
        int max = 0, itrMax = 0;
        while (rightIdx < cost.length) {
            itrMax += cost[rightIdx++];
            if (itrMax > maxCost)
                itrMax -= cost[leftIdx++];
            else
                max = Math.max(max, rightIdx-leftIdx);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(
                equalSubString(
                        "abcd",
                        "bcdf",
                        3
                ) == 3
        );

        System.out.println(
                equalSubString(
                        "abcd",
                        "cdef",
                        3
                ) == 1
        );

        System.out.println(
                equalSubString(
                        "abcd",
                        "acde",
                        0
                ) == 1
        );

        System.out.println(
                equalSubString(
                        "kgjeivnfj",
                        "mgkdzvnfj",
                        5
                ) == 4
        );
    }
}
