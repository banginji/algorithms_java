package com.abc.algorithms.leetcode.greedy;

public class _1800_MaxAscSubArray {
    private static int maxArray(int[] nums) {
        return maxSum(1, nums, nums[0], nums[0]);
    }

    private static int maxSum(int idx, int[] nums, int itrSum, int maxSum) {
        if (idx >= nums.length) return maxSum;
        if (nums[idx] > nums[idx - 1])
            return maxSum(idx + 1, nums, itrSum + nums[idx], Math.max(maxSum, itrSum + nums[idx]));
        else
            return maxSum(idx + 1, nums, nums[idx], maxSum);
    }

    public static void main(String[] args) {
        System.out.println(
                maxArray(new int[]{10, 20, 30, 5, 10, 50}) == 65
        );

        System.out.println(
                maxArray(new int[]{10, 20, 30, 40, 50}) == 150
        );

        System.out.println(
                maxArray(new int[]{12, 17, 15, 13, 10, 11, 12}) == 33
        );

        System.out.println(
                maxArray(new int[]{100, 10, 1}) == 100
        );
    }
}
