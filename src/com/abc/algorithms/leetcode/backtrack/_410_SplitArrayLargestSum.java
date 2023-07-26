package com.abc.algorithms.leetcode.backtrack;

import java.util.stream.IntStream;

public class _410_SplitArrayLargestSum {
    private static int maxSum(int[] nums, int m) {
        int sum = IntStream.of(nums).sum();

        int targetSum = sum / m;

        int res = Integer.MIN_VALUE;
        boolean[] taken = new boolean[nums.length];

        for (int idx = 0; idx < nums.length; idx++) {
            if (!taken[idx]) {
                Object[] itrRes = maxSum(nums, taken, idx, 0, targetSum);
                res = Math.max(res, (int) itrRes[1]);
            }
        }
        return res;
    }

    private static Object[] maxSum(int[] nums, boolean[] taken, int idx, int itrSum, int targetSum) {
        if (idx >= nums.length) return new Object[]{false, -1};
        int currSum = itrSum + nums[idx];
        if (!taken[idx] && currSum >= (targetSum - 2) && currSum <= (targetSum + 2)) {
            taken[idx] = true;
            return new Object[]{true, currSum};
        }
        Object[] res = maxSum(nums, taken, idx + 1, itrSum + nums[idx], targetSum);
        if (!taken[idx] && (boolean) res[0]) {
            taken[idx] = true;
            return res;
        }
        return maxSum(nums, taken, idx + 1, itrSum, targetSum);
    }

    public static void main(String[] args) {
        System.out.println(
                maxSum(
                        new int[]{7, 2, 5, 10, 8},
                        2
                ) == 18
        );

        System.out.println(
                maxSum(
                        new int[]{1, 2, 3, 4, 5},
                        2
                ) == 9
        );

        System.out.println(
                maxSum(
                        new int[]{1, 4, 4},
                        3
                ) == 4
        );
    }
}
