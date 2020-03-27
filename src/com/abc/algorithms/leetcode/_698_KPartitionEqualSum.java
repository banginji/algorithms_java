package com.abc.algorithms.leetcode;

import java.util.stream.IntStream;

public class _698_KPartitionEqualSum {
    // TODO - Not implemented
    private static boolean canPartition(int[] nums, int k) {
        int targetSum = IntStream.of(nums).sum();

        if (k % 2 == 0 && targetSum % k != 0)
            return false;

        targetSum /= k;

        return false;
    }

    public static void main(String[] args) {
        System.out.println(
                canPartition(
                        new int[]{2, 2, 2, 2}, 4
                )
        );
    }
}
