package com.abc.algorithms.leetcode.array;

public class _1493_LongestSubArray {
    private static int longestSubArray(int[] nums) {
        int maxLength = 1, skip = 1;
        for (int leftIdx = 0, rightIdx = leftIdx; leftIdx < nums.length; ++leftIdx) {
            skip >>= nums[leftIdx] == 0 ? 1 : 0;
            while (rightIdx < nums.length && skip != 4)
                skip <<= nums[rightIdx++] == 0 ? 1 : 0;
            maxLength = Math.max(maxLength, rightIdx - leftIdx);
        }
        return maxLength;
    }

    private static int dp(int[] nums) {
        return dp(nums, 0, 0, 1, 1, 1);
    }

    private static int dp(int[] nums, int leftIdx, int rightIdx, int dir, int maxLength, int skip) {
        if (leftIdx >= nums.length || rightIdx >= nums.length) return maxLength;
        if (dir > 0) skip <<= nums[rightIdx++] == 0 ? 1 : 0;
        if (dir < 0) skip >>= nums[leftIdx++] == 0 ? 1 : 0;
        if (skip == 4) {
            return dp(nums, leftIdx, rightIdx, -1, maxLength, skip);
        } else {
            maxLength = Math.max(maxLength, rightIdx - leftIdx - 2);
            return dp(nums, leftIdx, rightIdx, 1, maxLength, skip);
        }
    }

    public static void main(String[] args) {
        System.out.println(dp(new int[]{1, 1, 0, 1}));
//        System.out.println(dp(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
//        System.out.println(dp(new int[]{1, 1, 1}));
//        System.out.println(dp(new int[]{1, 1, 0, 0, 1, 1, 1, 0, 1}));
//        System.out.println(dp(new int[]{0, 0, 0}));
    }
}
