package com.abc.algorithms.leetcode.greedy;

public class _1664_FairArray {
    // TLE for large input array
    private static int fairArrayCount(int[] nums) {
        int result = 0;

        for (int iIdx = 0; iIdx < nums.length; iIdx++) {
            int even = 0, odd = 0;
            for (int jIdx = 0; jIdx < nums.length; jIdx++) {
                if (jIdx == iIdx) continue;

                if (jIdx > iIdx) {
                    if ((jIdx - 1) % 2 == 0)
                        even += nums[jIdx];
                    else
                        odd += nums[jIdx];
                    continue;
                }

                if (jIdx % 2 == 0)
                    even += nums[jIdx];
                else
                    odd += nums[jIdx];
            }
            if (even == odd) result++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                fairArrayCount(
                        new int[]{2, 1, 6, 4}
                ) == 1
        );
    }
}
