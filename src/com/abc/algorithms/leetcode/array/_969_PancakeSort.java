package com.abc.algorithms.leetcode.array;

import java.util.*;

public class _969_PancakeSort {
    private static List<Integer> pancakeSort(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int elem : arr) pq.offer(elem);
        List<Integer> res = new ArrayList<>();
        int pivotIdx = arr.length - 1;
        while (pq.size() > 0 && pivotIdx >= 0) {
            int nextElemIdx = find(pq.poll(), pivotIdx, arr);
            reverse(nextElemIdx, arr);
            res.add(nextElemIdx + 1);
            reverse(pivotIdx, arr);
            res.add(pivotIdx-- + 1);
        }
        return res;
    }

    private static void reverse(int endIdx, int[] arr) {
        int startIdx = 0;
        while (startIdx < endIdx) {
            arr[startIdx] -= arr[endIdx];
            arr[endIdx] += arr[startIdx];
            arr[startIdx] = arr[endIdx] - arr[startIdx];
            startIdx++;
            endIdx--;
        }
    }

    private static int find(int elem, int endIdx, int[] arr) {
        for (int idx = 0; idx <= endIdx; idx++) if (arr[idx] == elem) return idx;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(
                pancakeSort(
                        new int[]{3, 2, 4, 1}
                ).equals(List.of(3, 4, 2, 3, 1, 2, 1, 1))
        );

        System.out.println(
                pancakeSort(
                        new int[]{1, 2, 3}
                ).equals(List.of(3, 3, 2, 2, 1, 1))
        );
    }
}
