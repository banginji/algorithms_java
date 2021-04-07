package com.abc.algorithms.leetcode.array;

public class _1052_GrumpyOwner {
    private static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int[] prefixSum = new int[customers.length];
        prefixSum[0] = grumpy[0] * customers[0];
        for (int idx = 1; idx < customers.length; idx++)
            prefixSum[idx] = prefixSum[idx - 1] + (grumpy[idx] * customers[idx]);
        int maxSum = 0;
        for (int idx = 0; idx < customers.length - X; idx++) {
            maxSum = Math.max(maxSum, preSum(idx, prefixSum) + windowsSum(idx, idx + X, customers) + postSum(idx + X + 1, prefixSum));
        }
        return maxSum;
    }

    private static int windowsSum(int leftIdx, int rightIdx, int[] customers) {
        int sum = 0;
        for (int idx = leftIdx; idx <= rightIdx; idx++) sum += customers[idx];
        return sum;
    }

    private static int preSum(int rightIdx, int[] prefixSum) {
        if (rightIdx == 0) return 0;
        return prefixSum[rightIdx];
    }

    private static int postSum(int leftIdx, int[] prefixSum) {
        if (leftIdx < prefixSum.length - 1) return prefixSum[prefixSum.length - 1] - prefixSum[leftIdx];
        else return 0;
    }

    public static void main(String[] args) {
        System.out.println(
                maxSatisfied(
                        new int[]{1, 0, 1, 2, 1, 1, 7, 5},
                        new int[]{0, 1, 0, 1, 0, 1, 0, 1},
                        3
                ) == 16
        );
    }
}
