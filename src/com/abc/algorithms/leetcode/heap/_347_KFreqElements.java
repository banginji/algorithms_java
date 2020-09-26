package com.abc.algorithms.leetcode.heap;

import java.util.*;

public class _347_KFreqElements {
    private record NumFreq(int num, int freq) {
    }

    private static int[] kFreqElements(int[] nums, int k) {
        Map<Integer, Integer> numFreqMap = new HashMap<>();

        for (int num : nums) {
            numFreqMap.putIfAbsent(num, 0);
            numFreqMap.computeIfPresent(num, (key, value) -> value + 1);
        }

        PriorityQueue<NumFreq> pq = new PriorityQueue<>((numFreq1, numFreq2) -> numFreq2.freq() - numFreq1.freq());

        for (Map.Entry<Integer, Integer> entry : numFreqMap.entrySet())
            pq.offer(new NumFreq(entry.getKey(), entry.getValue()));

        List<Integer> result = new ArrayList<>();

        while (k-- > 0)
            result.add(pq.poll().num());

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(kFreqElements(new int[]{1}, 1))
        );
    }
}
