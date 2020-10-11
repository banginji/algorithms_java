package com.abc.algorithms.leetcode.hashtable;

public class _152_LargestPrdArray {
    private static int largestPrdArray(int[] nums) {
        int largestPrd = nums[0], minPrd = nums[0], maxPrd = nums[0];

        for (int idx = 1; idx < nums.length; idx++) {
            int possibleMin = nums[idx] * minPrd;
            int possibleMax = nums[idx] * maxPrd;

            minPrd = Math.min(nums[idx], Math.min(possibleMin, possibleMax));
            maxPrd = Math.max(nums[idx], Math.max(possibleMin, possibleMax));

            largestPrd = Math.max(largestPrd, maxPrd);
        }

        return largestPrd;
    }

    private static int largestPrdQuadComp(int[] nums) {
        int maxPrd = Integer.MIN_VALUE;
        for (int iIdx = 0; iIdx < nums.length; iIdx++) {
            int currentPrd = 1;
            for (int jIdx = iIdx; jIdx < nums.length; jIdx++)
                maxPrd = Math.max(currentPrd *= nums[jIdx], maxPrd);
        }

        return maxPrd;
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

        System.out.println(
                largestPrdArray(
                        new int[]{0, -2}
                ) == 0
        );

        System.out.println(
                largestPrdQuadComp(
                        new int[]{2, 3, -2, 4}
                ) == 6
        );

        System.out.println(
                largestPrdQuadComp(
                        new int[]{-2, 0, -1}
                ) == 0
        );

        System.out.println(
                largestPrdQuadComp(
                        new int[]{-2}
                ) == -2
        );

        System.out.println(
                largestPrdQuadComp(
                        new int[]{-2, -16, 8, 2, -1, 1}
                ) == 512
        );

        System.out.println(
                largestPrdArray(
                        new int[]{0, -2}
                ) == 0
        );
    }
}
