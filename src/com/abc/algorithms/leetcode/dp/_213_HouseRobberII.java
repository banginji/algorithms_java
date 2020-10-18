package com.abc.algorithms.leetcode.dp;

import com.abc.algorithms.leetcode.TimeIt;

public class _213_HouseRobberII {
    private static int houseRobber(int[] nums) {
        return Math.max(
                houseRobber(nums, 1, nums.length - 1),
                nums[0] + houseRobber(nums, 2, nums.length - 2)
        );
    }

    private static int houseRobber(int[] nums, int idx, int endIdx) {
        if (idx > endIdx) return 0;

        return Math.max(
                houseRobber(nums, idx + 1, endIdx),
                nums[idx] + houseRobber(nums, idx + 2, endIdx)
        );
    }

    private static int houseRobberDpTd(int[] nums) {
        Integer[] dp = new Integer[nums.length];

        return Math.max(
                houseRobberDpTd(dp, nums, 1, nums.length - 1),
                nums[0] + houseRobberDpTd(dp, nums, 2, nums.length - 2)
        );
    }

    private static int houseRobberDpTd(Integer[] dp, int[] nums, int idx, int endIdx) {
        if (idx > endIdx) return 0;
        if (dp[idx] != null) return dp[idx];

        return Math.max(
                houseRobberDpTd(dp, nums, idx + 1, endIdx),
                nums[idx] + houseRobberDpTd(dp, nums, idx + 2, endIdx)
        );
    }

    private static int houseRobberDpBu(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));

        int[] dp = new int[nums.length];

        return Math.max(
                houseRobberDpBu(dp, nums, 1, nums.length - 1),
                nums[0] + houseRobberDpBu(dp, nums, 2, nums.length - 2)
        );
    }

    private static int houseRobberDpBu(int[] dp, int[] nums, int startIdx, int endIdx) {
        dp[startIdx] = nums[startIdx];
        dp[startIdx + 1] = Math.max(nums[startIdx], nums[startIdx + 1]);

        for (int idx = startIdx + 2; idx <= endIdx; idx++)
            dp[idx] = Math.max(
                    dp[idx - 1],
                    nums[idx] + dp[idx - 2]
            );

        return dp[endIdx];
    }

    public static void main(String[] args) {
        System.out.println(
                houseRobber(
                        new int[]{2, 3, 2}
                ) == 3
        );

        System.out.println(
                houseRobber(
                        new int[]{1, 2, 3, 1}
                ) == 4
        );

        System.out.println(
                houseRobberDpTd(
                        new int[]{2, 3, 2}
                ) == 3
        );

        System.out.println(
                houseRobberDpTd(
                        new int[]{1, 2, 3, 1}
                ) == 4
        );

        System.out.println(
                houseRobberDpBu(
                        new int[]{2, 3, 2}
                ) == 3
        );

        System.out.println(
                houseRobberDpBu(
                        new int[]{1, 2, 3, 1}
                ) == 4
        );

        int itr = 0;
        System.out.println("TimeIt houseRobber");
        /**
         * Time taken: 33.762016617
         * Time taken: 31.06689838
         *
         * Time taken ~ 30 s
         */
        while (itr++ < 2)
            TimeIt.timeTaken(() -> houseRobber(new int[]{114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240}));

        itr = 0;
        System.out.println("TimeIt houseRobberDpTd");
        /**
         * Time taken: 38.4801395
         * Time taken: 38.535609768
         *
         * Time taken ~38 s
         */
        while (itr++ < 2)
            TimeIt.timeTaken(() -> houseRobberDpTd(new int[]{114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240}));

        itr = 0;
        System.out.println("TimeIt houseRobberDpBu");
        /**
         * Time taken: 7.16E-6
         * Time taken: 7.914E-6
         *
         * Time taken ~10e6 s
         */
        while (itr++ < 2)
            TimeIt.timeTaken(() -> houseRobberDpBu(new int[]{114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240}));
    }
}
