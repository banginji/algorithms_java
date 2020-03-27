package com.abc.algorithms.leetcode;

import java.util.PriorityQueue;

public class _1046_LastStoneWeight {
    private static Integer lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        for (int stone : stones)
            queue.add(stone);

        while (queue.size() > 1)
            queue.add(queue.remove() - queue.remove());

        return queue.peek();
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }
}
