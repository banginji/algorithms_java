package com.abc.algorithms.leetcode.array;

import java.util.*;

public class _1122_RelativeSortArray {
    private static int[] sortArray(int[] arr1, int[] arr2) {
        Map<Integer, List<Integer>> elemMap = new HashMap<>();
        for (int idx = 0; idx < arr1.length; idx++) elemMap.computeIfAbsent(arr1[idx], x -> new ArrayList<>()).add(idx);
        int[] res = new int[arr1.length];
        boolean[] seen = new boolean[arr1.length];
        Arrays.fill(seen, false);
        int resIdx = 0;
        for (int elem : arr2)
            for (Integer idx : elemMap.get(elem)) {
                seen[idx] = true;
                res[resIdx++] = elem;
            }
        for (int idx = 0; idx < seen.length; idx++) if (!seen[idx]) res[resIdx++] = arr1[idx];
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        sortArray(
                                new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19},
                                new int[]{2, 1, 4, 3, 9, 6}
                        )
                )
        );
    }
}
