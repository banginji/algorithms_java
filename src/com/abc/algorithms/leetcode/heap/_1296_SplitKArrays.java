package com.abc.algorithms.leetcode.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _1296_SplitKArrays {
    private static boolean isPossible(int[] nums, int k) {
        Arrays.sort(nums);

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int num : nums)
            if (map.getOrDefault(num - 1, new PriorityQueue<>()).size() > 0)
                map.computeIfAbsent(num, x -> new PriorityQueue<>()).offer(map.get(num - 1).poll() + 1);
            else
                map.computeIfAbsent(num, x -> new PriorityQueue<>()).offer(1);

        for (PriorityQueue<Integer> pq : map.values())
            while (pq.size() > 0)
                if (pq.poll() != k)
                    return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4)
        );
    }
}
