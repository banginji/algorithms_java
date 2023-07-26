package com.abc.algorithms.leetcode.slidingwindow;

public class _2302_SubArrayCount {
    private static int count(int[] nums, int k) {
        int itrSum = 0, count = 0;
        for (int rightIdx = 0, leftIdx = 0; rightIdx < nums.length; rightIdx++) {
            itrSum += nums[rightIdx];
            while (itrSum * (rightIdx - leftIdx + 1) >= k)
                itrSum -= nums[leftIdx++];
            count += (rightIdx - leftIdx + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                count(
                        new int[]{2, 1, 4, 3, 5},
                        10
                ) == 6
        );

        System.out.println(
                count(
                        new int[]{1, 1, 1},
                        5
                ) == 5
        );
    }
}
