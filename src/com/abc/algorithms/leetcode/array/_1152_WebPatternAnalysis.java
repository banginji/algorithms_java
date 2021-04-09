package com.abc.algorithms.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

public class _1152_WebPatternAnalysis {
    // The following does not solve the (ambiguous?) question
    private static List<String> patternAnalysis(String[] username, int[] timestamp, String[] website) {
        Map<String, int[]> map = new HashMap<>();
        for (int idx = 0; idx < username.length; idx++) {
            map.putIfAbsent(website[idx], new int[]{0, Integer.MAX_VALUE});
            final int finalIdx = idx;
            // Collect data for each website, the data being number of users(should it account for duplicates?) and earliest timestamp
            map.computeIfPresent(website[idx], (k, v) -> new int[]{v[0] + 1, Math.min(v[1], timestamp[finalIdx])});
        }
        // Sort based on most visits and if number of users are the same then sort website in lexical order
        PriorityQueue<Map.Entry<String, int[]>> pq = new PriorityQueue<>((a, b) -> {
            int bu = b.getValue()[0], au = a.getValue()[0];
            if (bu == au) return a.getKey().compareTo(b.getKey());
            return bu - au;
        });
        for (Map.Entry<String, int[]> entry : map.entrySet()) pq.offer(entry);
        Map.Entry<String, int[]>[] res = new Map.Entry[3];
        int count = 2;
        while (count >= 0 && pq.size() > 0) res[count--] = pq.poll();
        return Arrays.stream(res).sorted(Comparator.comparingInt(a -> a.getValue()[1])).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(
                patternAnalysis(
                        new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"},
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        new String[]{"home", "about", "career", "home", "cart", "maps", "career", "home", "about", "career"}
                )
        );
    }
}
