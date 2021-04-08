package com.abc.algorithms.leetcode.array;

import java.util.Arrays;

public class _1099_TwoSumLessThanK {
    private static int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int maxSum = Integer.MIN_VALUE;
        int leftIdx = 0, rightIdx = nums.length - 1;
        while (leftIdx < rightIdx)
            if (nums[leftIdx] + nums[rightIdx] < k)
                maxSum = Math.max(maxSum, nums[leftIdx++] + nums[rightIdx]);
            else rightIdx--;
        return maxSum == Integer.MIN_VALUE ? -1 : maxSum;
    }

    public static void main(String[] args) {
        System.out.println(
                twoSumLessThanK(
                        new int[]{34, 23, 1, 24, 75, 33, 54, 8},
                        60
                ) == 58
        );

        System.out.println(
                twoSumLessThanK(
                        new int[]{10, 20, 30},
                        15
                ) == -1
        );
    }
}
