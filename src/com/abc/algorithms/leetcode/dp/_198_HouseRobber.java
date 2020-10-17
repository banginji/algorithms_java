package com.abc.algorithms.leetcode.dp;

import java.util.Arrays;

public class _198_HouseRobber {
    private static int houseRobberSlow(int[] nums) {
        return robHouseSlow(nums, 0);
    }

    private static int robHouseSlow(int[] nums, int idx) {
        if (idx >= nums.length)
            return 0;

        return Math.max(
                robHouseSlow(nums, idx + 1),
                nums[idx] + robHouseSlow(nums, idx + 2)
        );
    }

    private static int houseRobberDpTd(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);

        return houseRobberDpTd(dp, nums, 0);
    }

    private static int houseRobberDpTd(int[] dp, int[] nums, int idx) {
        if (idx >= nums.length) return 0;
        if (dp[idx] != Integer.MIN_VALUE) return dp[idx];

        return Math.max(
                houseRobberDpTd(dp, nums, idx + 1),
                nums[idx] + houseRobberDpTd(dp, nums, idx + 2)
        );
    }

    private static int houseRobberDpBu(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int idx = 2; idx < nums.length; idx++)
            dp[idx] = Math.max(
                    dp[idx - 1],
                    nums[idx] + dp[idx - 2]
            );

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(
                houseRobberSlow(
                        new int[]{1, 2, 3, 1}
                ) == 4
        );

        System.out.println(
                houseRobberSlow(
                        new int[]{2, 7, 9, 3, 1}
                ) == 12
        );

        System.out.println(
                houseRobberDpTd(
                        new int[]{1, 2, 3, 1}
                ) == 4
        );

        System.out.println(
                houseRobberDpTd(
                        new int[]{2, 7, 9, 3, 1}
                ) == 12
        );

        System.out.println(
                houseRobberDpBu(
                        new int[]{1, 2, 3, 1}
                ) == 4
        );

        System.out.println(
                houseRobberDpBu(
                        new int[]{2, 7, 9, 3, 1}
                ) == 12
        );
    }
}
