package com.abc.algorithms.leetcode.array;

import java.util.Arrays;

public class _1053_PrevPerm {
    private static int[] prevPerm(int[] arr) {
        int idx = arr.length - 2;
        while (idx >= 0 && arr[idx] <= arr[idx + 1]) idx--;
        // return same array is continuously decreasing since there is no prev perm
        if (idx < 0) return arr;
        // find the next smaller largest number to arr[idx] in [idx, arr.length - 1] to swap with for the prev perm
        int revIdx = idx;
        while (revIdx + 1 < arr.length && arr[idx] > arr[revIdx + 1]) revIdx++;
        // this is to get the first occurrence of duplicate nums [7, 1, 1, 1] since revIdx will be the rightmost idx
        while (revIdx > idx && arr[revIdx] == arr[revIdx - 1]) revIdx--;
        // swap indices
        arr[idx] -= arr[revIdx];
        arr[revIdx] += arr[idx];
        arr[idx] = arr[revIdx] - arr[idx];
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(
                        prevPerm(new int[]{3, 2, 1}),
                        new int[]{3, 1, 2}
                )
        );

        System.out.println(
                Arrays.equals(
                        prevPerm(new int[]{1, 1, 5}),
                        new int[]{1, 1, 5}
                )
        );

        System.out.println(
                Arrays.equals(
                        prevPerm(new int[]{1, 9, 4, 6, 7}),
                        new int[]{1, 7, 4, 6, 9}
                )
        );

        System.out.println(
                Arrays.equals(
                        prevPerm(new int[]{3, 1, 1, 3}),
                        new int[]{1, 3, 1, 3}
                )
        );
    }
}
