package com.abc.algorithms.leetcode.backtrack;

import java.util.stream.IntStream;

public class _698_KPartitionEqualSum {
    private static boolean canPartition(int[] nums, int k) {
        int targetSum = IntStream.of(nums).sum();

        if (k % 2 == 0 && targetSum % k != 0)
            return false;

        targetSum /= k;

        boolean[] taken = new boolean[nums.length];
        boolean res = true;
        for (int idx = 0; idx < nums.length; idx++)
            if (!taken[idx])
                res &= count(nums, taken, idx, 0, targetSum);
        return res;
    }

    private static boolean count(int[] nums, boolean[] taken, int idx, int itrSum, int target) {
        if (idx >= nums.length) return false;
        if (!taken[idx] && itrSum + nums[idx] == target) {
            taken[idx] = true;
            return true;
        }
        if (!taken[idx] && count(nums, taken, idx + 1, itrSum + nums[idx], target)) {
            taken[idx] = true;
            return true;
        } else
            return count(nums, taken, idx + 1, itrSum, target);
    }

    public static void main(String[] args) {
        System.out.println(
                canPartition(
                        new int[]{4, 3, 2, 3, 5, 2, 1}, 4
                )
        );

        System.out.println(
                !canPartition(
                        new int[]{1, 2, 3, 4}, 3
                )
        );

        System.out.println(
                canPartition(
                        new int[]{2, 2, 2, 2}, 4
                )
        );
    }
}
