package com.abc.algorithms.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _3545_MinDelKDistinct {
    public int minDeletion(String s, int k) {
        char[] ca = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : ca) {
            map.putIfAbsent(c, 0);
            map.computeIfPresent(c, (key, value) -> value + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for (Map.Entry<Character, Integer> entry: map.entrySet()) pq.offer(entry);
        int result = 0;
        while (pq.size() > k) result += pq.poll().getValue();

        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                new _3545_MinDelKDistinct().minDeletion("aabb", 2) == 0
        );
        System.out.println(
                new _3545_MinDelKDistinct().minDeletion("abc", 2) == 1
        );
        System.out.println(
                new _3545_MinDelKDistinct().minDeletion("yyyzz", 1) == 2
        );
    }
}
