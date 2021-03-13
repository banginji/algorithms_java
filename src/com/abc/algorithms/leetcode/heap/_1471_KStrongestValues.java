package com.abc.algorithms.leetcode.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _1471_KStrongestValues {
    private static int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int median = arr[(arr.length - 1) / 2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        for (int num : arr) pq.offer(new int[]{num, Math.abs(num - median)});
        List<Integer> res = new ArrayList<>();
        while (pq.size() > 0 && k-- > 0) res.add(pq.poll()[0]);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.equals(
                        getStrongest(new int[]{1, 2, 3, 4, 5}, 2),
                        new int[]{5, 1}
                )
        );

        System.out.println(
                Arrays.equals(
                        getStrongest(new int[]{1, 1, 3, 5, 5}, 2),
                        new int[]{5, 5}
                )
        );

        System.out.println(
                Arrays.equals(
                        getStrongest(new int[]{6, 7, 11, 7, 6, 8}, 5),
                        new int[]{11, 8, 6, 6, 7}
                )
        );

        System.out.println(
                Arrays.equals(
                        getStrongest(new int[]{6, -3, 7, 2, 11}, 3),
                        new int[]{-3, 11, 2}
                )
        );
    }
}
