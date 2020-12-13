package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _851_LoudAndRich {
    private static int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, List<Integer>> richMap = new HashMap<>();

        for (int[] rich : richer) richMap.computeIfAbsent(rich[1], x -> new ArrayList<>()).add(rich[0]);

        int[] result = new int[quiet.length];
        Arrays.fill(result, -1);

        for (int person = 0; person < quiet.length; person++) dfs(person, quiet, result, richMap);

        return result;
    }

    private static int dfs(int person, int[] quiet, int[] result, Map<Integer, List<Integer>> richMap) {
        if (result[person] >= 0) return result[person];
        result[person] = person;

        for (Integer rp : richMap.getOrDefault(person, Collections.emptyList()))
            if (quiet[result[person]] > quiet[dfs(rp, quiet, result, richMap)])
                result[person] = result[rp];

        return result[person];
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(
                        loudAndRich(
                                new int[][]{
                                        new int[]{1, 0},
                                        new int[]{2, 1},
                                        new int[]{3, 1},
                                        new int[]{3, 7},
                                        new int[]{4, 3},
                                        new int[]{5, 3},
                                        new int[]{6, 3}
                                },
                                new int[]{3, 2, 5, 4, 6, 1, 7, 0}
                        ),
                        new int[]{5, 5, 2, 5, 4, 5, 6, 7}
                )
        );
    }
}
