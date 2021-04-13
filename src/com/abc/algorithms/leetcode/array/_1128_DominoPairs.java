package com.abc.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class _1128_DominoPairs {
    private static int numPairs(int[][] dominoes) {
        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int[] domino : dominoes) {
            // some combination of the two nos will yield a unique key which can be used for comparison
            int a = domino[0], b = domino[1];
            int key = 10 * Math.min(a, b) + Math.max(a, b);
            keyMap.putIfAbsent(key, 0);
            keyMap.computeIfPresent(key, (k, v) -> v + 1);
        }
        // combination count of pairs is evaluated for the result
        int res = 0;
        for (int count : keyMap.values()) res += (count * (count-1))/2;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                numPairs(
                        new int[][]{
                                new int[]{1, 2},
                                new int[]{2, 1},
                                new int[]{3, 4},
                                new int[]{5, 6}
                        }
                ) == 1
        );
    }
}
