package com.abc.algorithms.leetcode.dp;

public class _53_MaxSumSubArray {
    /**
     * Kadane's algorithm
     * https://leetcode.com/problems/maximum-subarray/discuss/875312/Java-Solution-Kadane's-Algorithm-with-Detailed-Explanation
     */
    private static int maxSum(int[] nums) {
        int maxSum = nums[0], currentMaxSum = nums[0];

        for (int idx = 1; idx < nums.length; idx++) {
            currentMaxSum = Math.max(nums[idx], currentMaxSum + nums[idx]);
            maxSum = Math.max(maxSum, currentMaxSum);
        }

        return maxSum;
    }

    // Brute Force
    private static int maxSumQuadComp(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for (int iIdx = 0; iIdx < nums.length; iIdx++) {
            int sum = 0;
            for (int jIdx = iIdx; jIdx < nums.length; jIdx++)
                maxSum = Math.max(sum += nums[jIdx], maxSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(
                maxSum(
                        new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}
                ) == 6
        );

        System.out.println(
                maxSum(
                        new int[]{1}
                ) == 1
        );

        System.out.println(
                maxSum(
                        new int[]{0}
                ) == 0
        );

        System.out.println(
                maxSum(
                        new int[]{-1}
                ) == -1
        );

        System.out.println(
                maxSum(
                        new int[]{-2147483647}
                ) == -2147483647
        );

        System.out.println(
                maxSumQuadComp(
                        new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}
                ) == 6
        );

        System.out.println(
                maxSumQuadComp(
                        new int[]{1}
                ) == 1
        );

        System.out.println(
                maxSumQuadComp(
                        new int[]{0}
                ) == 0
        );

        System.out.println(
                maxSumQuadComp(
                        new int[]{-1}
                ) == -1
        );

        System.out.println(
                maxSumQuadComp(
                        new int[]{-2147483647}
                ) == -2147483647
        );
    }
}
