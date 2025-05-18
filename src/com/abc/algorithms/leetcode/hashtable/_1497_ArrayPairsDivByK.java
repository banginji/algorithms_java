package com.abc.algorithms.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class _1497_ArrayPairsDivByK {
    // DOES NOT WORK: REDO
    private static boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int elem : arr) {
            int key = elem % k >= 0 ? elem % k : k + (elem % k);
            map.putIfAbsent(key, 0);
            map.computeIfPresent(key, (k1, v) -> v + 1);
        }

        if (map.containsKey(0) && map.get(0) % 2 == 0) {
            count += map.get(0) / 2;
            map.remove(0);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0 && map.get(k - entry.getKey()) > 0) {
                map.compute(entry.getKey(), (k1, v) -> v - 1);
                map.compute(k - entry.getKey(), (k1, v) -> v - 1);
                count++;
            }
        }

        return count == arr.length / 2;
    }

    public static void main(String[] args) {
        System.out.println(
                canArrange(
                        new int[]{-1, 2, -3, 4, -5, 6, -7, 8, -9, 10},
                        5
                )
        );
    }
}
