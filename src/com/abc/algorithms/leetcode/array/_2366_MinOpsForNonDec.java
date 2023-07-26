package com.abc.algorithms.leetcode.array;

public class _2366_MinOpsForNonDec {
    private static int count(int[] nums) {
        int count = 0, rem = nums[nums.length - 1];
        for (int idx = nums.length - 2; idx >= 0; idx--) {
            if (rem < nums[idx]) {
                int itrRem = nums[idx] % rem;
                if (itrRem == 0) {
                    count += nums[idx] / rem - 1;
                } else {
                    count += nums[idx] / rem;
                    rem = itrRem;
                }
            } else
                rem = nums[idx];
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                count(
                        new int[]{3, 9, 3}
                )
        );

        System.out.println(
                count(
                        new int[]{1, 2, 3, 4, 5}
                )
        );

        System.out.println(
                count(
                        new int[]{1, 7, 3, 14, 10}
                )
        );

        // 6345
        System.out.println(
                count(
                        new int[]{35, 472, 1235, 4, 23, 99, 24742, 47, 2690, 235, 89323, 78, 345, 987, 24465, 975, 43, 878, 2363, 978, 677}
                )
        );
    }
}
