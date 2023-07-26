package com.abc.algorithms.leetcode.dp;

public class _2311_LongestBinarySubSeq {
    private static int lb(String s, int k) {
        return lb(s.length() - 1, 0, 0, s, k, 0);
    }

    private static int lb(int idx, int prevSum, int power, String s, int k, int length) {
        if (idx < 0) return length;
        double eval = (s.charAt(idx) - '0') * Math.pow(2, power);
        if (prevSum + eval <= k)
            return Math.max(
                    lb(idx - 1, (int) (prevSum + eval), power + 1, s, k, length + 1),
                    lb(idx - 1, prevSum, power, s, k, length)
            );
        else
            return lb(idx - 1, prevSum, power, s, k, length);
    }

    // does not work
    private static int lbDp(String s, int k) {
        int[] nums = new int[s.length() + 1];
        nums[nums.length - 1] = Integer.MIN_VALUE;
        for (int idx = 1; idx < nums.length; idx++) nums[idx] = s.charAt(idx - 1) - '0';
        Integer[][] dp = new Integer[nums.length][nums.length];
        return lbDp(nums.length - 2, dp, nums.length - 1, 0, 0, nums, k, 0);
    }

    private static int lbDp(int idx, Integer[][] dp, int prevIdx, int prevSum, int power, int[] nums, int k, int length) {
        if (idx < 0) return length;
        if (dp[idx][prevIdx] != null) return dp[idx][prevIdx];
        double eval = nums[idx] * Math.pow(2, power);
        if (prevSum + eval <= k)
            return dp[idx][prevIdx] = Math.max(
                    lbDp(idx - 1, dp, idx, (int) (prevSum + eval), power + 1, nums, k, length + 1),
                    lbDp(idx - 1, dp, idx, prevSum, power, nums, k, length)
            );
        return dp[idx][prevIdx] = lbDp(idx - 1, dp, idx, prevSum, power, nums, k, length);
    }

    // keep zeros to the max on the left to maximize the length
    // other than that calculate the ones from right to left
    // and make sure that the sum is less than k
    // The following works(probably does not for some edge case)
    private static int lpc(String s, int k) {
        int[] nums = new int[s.length()];
        for (int idx = 0; idx < nums.length; idx++) nums[idx] = (s.charAt(idx) - '0');
        int rightOneIdx = nums.length - 1;
        for (int idx = nums.length - 1; idx >= 0; idx--)
            if (nums[idx] == 1) {
                rightOneIdx = idx;
                break;
            }
        int res = 0;
        for (int idx = rightOneIdx - 1; idx >= 0; idx--)
            if (nums[idx] == 0)
                res++;
        int power = 0, itrSum = 0;
        for (int idx = rightOneIdx; idx >= 0; idx--) {
            if (nums[idx] == 1 && itrSum + nums[idx] * (int) Math.pow(2, power) <= k) {
                res++;
                power++;
            }
            if (nums[idx] == 0) power++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                lb("1001010", 5) == 5
        );

        System.out.println(
                lb("00101001", 1) == 6
        );

        System.out.println(
                lbDp("1001010", 5) == 5
        );

        System.out.println(
                lbDp("00101001", 1) == 6
        );

        System.out.println(
                lpc("1001010", 5) == 5
        );

        System.out.println(
                lpc("00101001", 1) == 6
        );
        System.out.println(
                lpc("1010010011100", 3) == 7
        );
    }
}
