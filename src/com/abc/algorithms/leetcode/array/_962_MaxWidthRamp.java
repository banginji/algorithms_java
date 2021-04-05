package com.abc.algorithms.leetcode.array;

import java.util.TreeMap;

public class _962_MaxWidthRamp {
    private static int maxWidthRamp(int[] A) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int maxWidth = 0;
        for (int idx = 0; idx < A.length; idx++) {
            if (tm.floorKey(A[idx]) == null) tm.put(A[idx], idx);
            else maxWidth = Math.max(maxWidth, idx - tm.get(tm.floorKey(A[idx])));
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        System.out.println(
                maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}) == 4
        );

        System.out.println(
                maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}) == 7
        );
    }
}
