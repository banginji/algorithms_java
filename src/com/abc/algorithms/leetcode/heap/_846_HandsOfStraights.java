package com.abc.algorithms.leetcode.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _846_HandsOfStraights {
    private static boolean isNStraightHand(int[] hands, int w) {
        Arrays.sort(hands);

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int hand : hands)
            if (map.getOrDefault(hand - 1, new PriorityQueue<>()).size() > 0)
                map.computeIfAbsent(hand, x -> new PriorityQueue<>()).offer(map.get(hand - 1).poll() + 1);
            else
                map.computeIfAbsent(hand, x -> new PriorityQueue<>()).offer(1);

        for (PriorityQueue<Integer> pq : map.values())
            while (pq.size() > 0)
                if (pq.poll() != w)
                    return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4)
        );
    }
}
