package com.abc.algorithms.leetcode.dp;

import com.abc.algorithms.leetcode.TimeIt;

import java.util.Arrays;
import java.util.stream.IntStream;

public class _300_LIS {
    private static int lis(int[] nums) {
        return lis(0, nums, Integer.MIN_VALUE);
    }

    private static int lis(int idx, int[] nums, int prevElem) {
        if (idx > nums.length - 1) return 0;
        if (nums[idx] > prevElem) return Math.max(1 + lis(idx + 1, nums, nums[idx]), lis(idx + 1, nums, prevElem));
        else return lis(idx + 1, nums, prevElem);
    }

    private static int lisDp(int[] nums) {
        int[] itrNums = new int[nums.length + 1];
        itrNums[0] = Integer.MIN_VALUE;
        System.arraycopy(nums, 0, itrNums, 1, nums.length);

        Integer[][] dp = new Integer[nums.length + 1][nums.length + 1];
        return lisDp(1, itrNums, 0, dp);
    }

    private static int lisDp(int idx, int[] nums, int prevIdx, Integer[][] dp) {
        if (idx > nums.length - 1 || prevIdx > nums.length - 1) return 0;

        if (dp[idx][prevIdx] != null) return dp[idx][prevIdx];
        if (nums[idx] > nums[prevIdx]) return dp[idx][prevIdx] = Math.max(1 + lisDp(idx + 1, nums, idx, dp), lisDp(idx + 1, nums, prevIdx, dp));
        else return dp[idx][prevIdx] = lisDp(idx + 1, nums, prevIdx, dp);
    }

    private static int lISDp(int[] nums) {
        int[] lengths = new int[nums.length];
        Arrays.fill(lengths, 1);

        for (int iIdx = 0; iIdx < nums.length; iIdx++)
            for (int jIdx = 0; jIdx < iIdx; jIdx++)
                if (nums[jIdx] < nums[iIdx])
                    lengths[iIdx] = Math.max(lengths[iIdx], 1 + lengths[jIdx]);

        return Arrays.stream(lengths).max().orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(
                lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) == 4
        );

        System.out.println(
                lis(new int[]{4, 10, 4, 3, 8, 9}) == 3
        );

        System.out.println(
                lisDp(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) == 4
        );

        System.out.println(
                lisDp(new int[]{4, 10, 4, 3, 8, 9}) == 3
        );

        System.out.println(
                lisDp(IntStream.range(0, 2500).boxed().mapToInt(Integer::intValue).toArray()) == 2500
        );

        // ~0.5 sec
        TimeIt.timeTaken(() -> lisDp(IntStream.range(0, 2500).boxed().mapToInt(Integer::intValue).toArray()));

        System.out.println(
                lISDp(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) == 4
        );

        System.out.println(
                lISDp(new int[]{4, 10, 4, 3, 8, 9}) == 3
        );

        System.out.println(
                lISDp(IntStream.range(0, 2500).boxed().mapToInt(Integer::intValue).toArray()) == 2500
        );

        // ~0.02 s; times out for non-DP approach
        TimeIt.timeTaken(() -> lISDp(IntStream.range(0, 2500).boxed().mapToInt(Integer::intValue).toArray()));
    }
}
