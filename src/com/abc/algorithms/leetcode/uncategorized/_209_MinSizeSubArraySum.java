package com.abc.algorithms.leetcode.uncategorized;

public class _209_MinSizeSubArraySum {
    private static int minSubArray(int s, int[] nums) {
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int startIdx = 0;

        for (int idx = 0; idx < nums.length; idx++) {
            sum += nums[idx];
            while (sum >= s) {
                minLength = Math.min(idx - startIdx + 1, minLength);
                sum -= nums[startIdx];
                startIdx++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // For reference
    private static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) { //basic validations
            return 0;
        }
        //The basic idea is to use sliding window patter, here the trick is
        //to adjust the size of the window dynamically while satisfying the constraint
        int windowStart = 0, sum = 0, minWindowLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) { //Try and reduce the window size as much as possible.
                minWindowLength = Math.min(minWindowLength, i - windowStart + 1);
                sum -= nums[windowStart];
                windowStart++;
            }
        }
        return minWindowLength == Integer.MAX_VALUE ? 0 : minWindowLength;

    }

    public static void main(String[] args) {
        System.out.println(minSubArray(4, new int[]{1, 1, 3}));
    }
}
