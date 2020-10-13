package com.abc.algorithms.leetcode.hashtable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class _697_ArrayDegree {
    private static int degree(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int longest = Integer.MIN_VALUE;

        for (int idx = 0; idx < nums.length; idx++) {
            map.putIfAbsent(nums[idx], new int[]{idx, idx, 0});
            final int finalIdx = idx;
            map.computeIfPresent(nums[idx], (k, v) -> new int[]{v[0], finalIdx, v[2] + 1});
            longest = Math.max(longest, map.get(nums[idx])[2]);
        }

        final int finalLongest = longest;
        int[] res = map.values().stream()
                .filter(value -> value[2] == finalLongest)
                .min(Comparator.comparingInt(a -> a[1] - a[0]))
                .orElse(new int[]{0, 0, 0});

        return res[1] - res[0] + 1;
    }

    public static void main(String[] args) {
        System.out.println(
                degree(
                        new int[]{1, 2, 2, 3, 1}
                ) == 2
        );

        System.out.println(
                degree(
                        new int[]{1, 2, 2, 3, 1, 4, 2}
                ) == 6
        );
    }
}
