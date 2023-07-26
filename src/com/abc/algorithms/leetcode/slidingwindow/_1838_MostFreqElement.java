package com.abc.algorithms.leetcode.slidingwindow;

import java.util.Arrays;

public class _1838_MostFreqElement {
    private static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int maxFreq = 1;
        for (int firstIdx = nums.length - 1, secondIdx = firstIdx; firstIdx >= 0; firstIdx--) {
            int itrK = k;
            for (; secondIdx >= 0 && itrK >= nums[firstIdx] - nums[secondIdx]; secondIdx--)
                itrK -= nums[firstIdx] - nums[secondIdx];
            maxFreq = Math.max(maxFreq, firstIdx - secondIdx);
        }
        return maxFreq;
    }

    public static void main(String[] args) {
        System.out.println(
                maxFrequency(
                        new int[]{1, 1, 1, 1, 1, 2, 3, 3, 4},
                        5
                ) == 6
        );

        System.out.println(
                maxFrequency(
                        new int[]{1, 2, 4},
                        5
                ) == 3
        );

        System.out.println(
                maxFrequency(
                        new int[]{1, 4, 8, 13},
                        5
                ) == 2
        );

        System.out.println(
                maxFrequency(
                        new int[]{3, 9, 6},
                        1
                ) == 1
        );
    }
}
