package com.abc.algorithms.leetcode.array;

public class _1493_LongestSubArray {
    private static int longestSubArray(int[] nums) {
        int zerosCount = 0, maxLength = Integer.MIN_VALUE;
        int leftIdx = 0, rightIdx = 0;
        while (rightIdx < nums.length) {
            if (nums[rightIdx] == 0) zerosCount++;
            while (zerosCount > 1) {
                if (nums[leftIdx] == 0) zerosCount--;
                if (++leftIdx == rightIdx) break;
            }
            maxLength = Math.max(maxLength, rightIdx - leftIdx);
            rightIdx++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestSubArray(new int[]{1, 1, 0, 1}) == 3);
        System.out.println(longestSubArray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}) == 5);
        System.out.println(longestSubArray(new int[]{1, 1, 1}) == 2);
        System.out.println(longestSubArray(new int[]{1, 1, 0, 0, 1, 1, 1, 0, 1}) == 4);
        System.out.println(longestSubArray(new int[]{0, 0, 0}) == 0);
    }
}
