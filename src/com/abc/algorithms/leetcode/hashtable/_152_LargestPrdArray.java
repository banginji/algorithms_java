package com.abc.algorithms.leetcode.hashtable;

public class _152_LargestPrdArray {
    private static int largestPrdArray(int[] nums) {
        int largestPrd = nums[0], prd = nums[0];

        for (int idx = 1; idx < nums.length; idx++) {
            int newPrd = prd * nums[idx];
            if (newPrd >= prd) {
                prd = newPrd;
                largestPrd = Math.max(largestPrd, newPrd);
            } else
                prd = 1;
        }

        return largestPrd;
    }

    public static void main(String[] args) {
        System.out.println(
                largestPrdArray(
                        new int[]{2, 3, -2, 4}
                ) == 6
        );

        System.out.println(
                largestPrdArray(
                        new int[]{-2, 0, -1}
                ) == 0
        );

        System.out.println(
                largestPrdArray(
                        new int[]{-2}
                ) == -2
        );

        System.out.println(
                largestPrdArray(
                        new int[]{-2, -16, 8, 2, -1, 1}
                ) == 512
        );
    }
}
