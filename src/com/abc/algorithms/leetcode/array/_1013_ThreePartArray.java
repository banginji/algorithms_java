package com.abc.algorithms.leetcode.array;

import java.util.Arrays;

public class _1013_ThreePartArray {
    private static boolean splitArrayCount(int[] arr) {
        int splitSum = Arrays.stream(arr).sum() / 3;
        int leftIdx = 0, rightIdx = arr.length - 1;
        int leftSum = 0, rightSum = 0;
        while (leftSum < splitSum) leftSum += arr[leftIdx++];
        while (rightSum < splitSum) rightSum += arr[rightIdx--];
        if (leftIdx > rightIdx) return false;
        int midSum = 0;
        for (int idx = leftIdx; idx <= rightIdx; idx++) midSum += arr[idx];
        return midSum == splitSum;
    }

    public static void main(String[] args) {
        System.out.println(
                splitArrayCount(
                        new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1}
                )
        );

        System.out.println(
                !splitArrayCount(
                        new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1}
                )
        );

        System.out.println(
                splitArrayCount(
                        new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4}
                )
        );
    }
}
