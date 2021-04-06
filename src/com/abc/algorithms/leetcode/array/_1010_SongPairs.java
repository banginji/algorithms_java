package com.abc.algorithms.leetcode.array;

import java.util.*;

public class _1010_SongPairs {
    private static int songPairCount(int[] time) {
        int count = 0;
        Map<Integer, Integer> modMap = new HashMap<>();
        for (int t : time) {
            // t % 60 gives the mod and it is subtracted from 60 (60 - t % 60) to get the complement to track it
            // wrapping % 60 is for taking care the edge case when t is 60 or 0
            count += modMap.getOrDefault((60 - t % 60) % 60, 0);
            modMap.putIfAbsent(t % 60, 0);
            modMap.computeIfPresent(t % 60, (k, v) -> v + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                songPairCount(
                        new int[]{30, 20, 150, 100, 40}
                ) == 3
        );

        System.out.println(
                songPairCount(
                        new int[]{60, 60, 60}
                ) == 3
        );
    }
}
