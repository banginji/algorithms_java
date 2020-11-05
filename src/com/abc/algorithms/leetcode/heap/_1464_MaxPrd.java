package com.abc.algorithms.leetcode.heap;

import java.util.PriorityQueue;

public class _1464_MaxPrd {
    private static int maxPrd(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int num : nums) pq.offer(num);

        int result = 1;
        int numOfElems = 2;
        while (pq.size() > 0 && numOfElems-- >= 1)
            result *= (pq.poll() - 1);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                maxPrd(new int[]{3, 4, 5, 2}) == 12
        );

        System.out.println(
                maxPrd(new int[]{1, 5, 4, 5}) == 16
        );

        System.out.println(
                maxPrd(new int[]{3, 7}) == 12
        );
    }
}
