package com.abc.algorithms.leetcode.array;

public class _1151_MinSwapsToGroup {
    private static int minSwapsCount(int[] data) {
        // find min zero count in max ones long window sliding window
        int onesCount = 0;
        for (int d : data) if (d == 1) onesCount++;
        int itrZeros = 0;
        for (int idx = 0; idx < onesCount; idx++) if (data[idx] == 0) itrZeros++;
        int minZeros = itrZeros, leftIdx = 0;
        for (int idx = onesCount; idx < data.length; idx++) {
            if (data[idx] == 0) itrZeros++;
            if (data[leftIdx++] == 0) itrZeros--;
            minZeros = Math.min(minZeros, itrZeros);
        }
        return minZeros;
    }

    public static void main(String[] args) {
        System.out.println(
                minSwapsCount(
                        new int[]{1, 0, 1, 0, 1}
                ) == 1
        );

        System.out.println(
                minSwapsCount(
                        new int[]{0, 0, 0, 1, 0}
                ) == 0
        );

        System.out.println(
                minSwapsCount(
                        new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}
                ) == 3
        );

        System.out.println(
                minSwapsCount(
                        new int[]{1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1}
                ) == 8
        );
    }
}
