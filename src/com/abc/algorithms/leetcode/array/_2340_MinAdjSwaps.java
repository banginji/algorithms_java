package com.abc.algorithms.leetcode.array;

public class _2340_MinAdjSwaps {
    private static int minSwaps(int[] nums) {
        if (nums.length == 1) return 0;
        int[] min = new int[]{nums[0], 0}, max = new int[]{nums[0], 0};
        for (int idx = 1; idx < nums.length; idx++) {
            if (min[0] > nums[idx]) min = new int[]{nums[idx], idx};
            if (max[0] <= nums[idx]) max = new int[]{nums[idx], idx};
        }
        return min[1] < max[1] ? (min[1] + nums.length - 1 - max[1]) : (min[1] + nums.length - 2 - max[1]);
    }

    public static void main(String[] args) {
        System.out.println(
                minSwaps(
                        new int[]{3, 4, 5, 5, 3, 1}
                ) == 6
        );

        System.out.println(
                minSwaps(
                        new int[]{9}
                ) == 0
        );

        System.out.println(
                minSwaps(
                        new int[]{4, 6, 8, 9, 2, 4, 7, 2, 34, 8, 24, 26, 9, 21, 99, 23, 6}
                ) == 6
        );

        System.out.println(
                minSwaps(
                        new int[]{7, 1, 53, 7, 1234, 87, 34, 7, 23, 66, 13, 87, 13, 984, 235, 864, 2454, 543, 13, 4, 2, 1354, 235, 3532, 45, 463, 1, 42, 341, 3151}
                ) == 7
        );
    }
}
