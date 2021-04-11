package com.abc.algorithms.leetcode.uncategorized;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class _451_SortCharsByFreq {
    private static String frequencySort(String s) {
        Map<Character, Integer> elemMap = new HashMap<>();
        for (char c: s.toCharArray()) {
            if (elemMap.containsKey(c)) {
                Integer count = elemMap.get(c);
                elemMap.put(c, ++count);
            } else
                elemMap.put(c, 1);
        }

        StringBuilder sb = new StringBuilder();

        elemMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .collect(Collectors.toList())
                .forEach(entry -> sb.append(String.valueOf(entry.getKey()).repeat(entry.getValue())));

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("treE"));
    }
}
