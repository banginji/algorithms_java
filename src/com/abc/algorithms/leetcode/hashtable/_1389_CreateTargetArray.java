package com.abc.algorithms.leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1389_CreateTargetArray {
    private static int[] createArray(int[] nums, int[] indices) {
        List<Integer> result = new ArrayList<>();

        for (int idx = 0; idx < nums.length; idx++)
            result.add(indices[idx], nums[idx]);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(
                        createArray(
                                new int[]{0, 1, 2, 3, 4},
                                new int[]{0, 1, 2, 2, 1}
                        ),
                        new int[]{0, 4, 1, 3, 2}
                )
        );

        System.out.println(
                Arrays.equals(
                        createArray(
                                new int[]{1, 2, 3, 4, 0},
                                new int[]{0, 1, 2, 3, 0}
                        ),
                        new int[]{0, 1, 2, 3, 4}
                )
        );
    }
}
