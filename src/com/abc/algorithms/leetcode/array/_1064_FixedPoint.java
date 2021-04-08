package com.abc.algorithms.leetcode.array;

public class _1064_FixedPoint {
    private static int fixedPoint(int[] nums) {
        return bs(0, nums.length - 1, nums);
    }

    private static int bs(int left, int right, int[] nums) {
        int mid = (left + right) / 2;
        if (right - left <= 1) {
            if (nums[right] == right) return right;
            else if (nums[left] == left) return left;
            else return -1;
        }
        if (nums[mid] - mid < 0)
            return bs(mid, right, nums);
        else
            return bs(left, mid, nums);
    }

    public static void main(String[] args) {
        System.out.println(
                fixedPoint(
                        new int[]{-10, -5, 0, 3, 7}
                ) == 3
        );

        System.out.println(
                fixedPoint(
                        new int[]{0, 2, 5, 8, 17}
                ) == 0
        );

        System.out.println(
                fixedPoint(
                        new int[]{-10, -5, 3, 4, 7, 9}
                ) == -1
        );
    }
}
