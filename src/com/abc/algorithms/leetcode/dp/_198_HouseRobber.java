package com.abc.algorithms.leetcode.dp;

import com.abc.algorithms.leetcode.TimeIt;

import java.util.Arrays;

// DP Template
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

        int itr = 0;
        System.out.println("TimeIt houseRobberSlow");
        /**
         * Time taken: 36.601678411
         * Time taken: 37.179172549
         * Time taken: 35.679414024
         *
         * Time taken ~36 s
         */
        while (itr++ < 2)
            TimeIt.timeTaken(() -> houseRobberSlow(new int[]{114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240}));

        itr = 0;
        System.out.println("TimeIt houseRobberDpTd");
        /**
         * Time taken: 40.558891072
         * Time taken: 40.100649711
         * Time taken: 41.278013967
         *
         * Time taken ~40 s
         */
        while (itr++ < 2)
            TimeIt.timeTaken(() -> houseRobberDpTd(new int[]{114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240}));

        itr = 0;
        System.out.println("TimeIt houseRobberDpBu");
        /**
         * Time taken: 5.847E-6
         * Time taken: 6.468E-6
         * Time taken: 3.623E-6
         *
         * Time taken ~10e6 s
         */
        while (itr++ < 2)
            TimeIt.timeTaken(() -> houseRobberDpBu(new int[]{114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240}));
    }
}
