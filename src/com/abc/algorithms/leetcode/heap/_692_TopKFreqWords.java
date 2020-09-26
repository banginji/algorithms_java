package com.abc.algorithms.leetcode.heap;

import java.util.*;

public class _692_TopKFreqWords {
    private record WordFreq(String word, Integer freq) {
    }

    private static List<String> topKFreqWords(String[] words, int k) {
        PriorityQueue<WordFreq> pq = new PriorityQueue<>((wf1, wf2) -> {
            if (wf1.freq() == wf2.freq())
                return wf1.word().compareTo(wf2.word());
            return wf2.freq() - wf1.freq();
        });

        Map<String, Integer> wordFreq = new HashMap<>();

        for (String word : words) {
            wordFreq.putIfAbsent(word, 0);
            wordFreq.computeIfPresent(word, (key, value) -> value + 1);
        }

        for (Map.Entry<String, Integer> entry : wordFreq.entrySet())
            pq.offer(new WordFreq(entry.getKey(), entry.getValue()));

        List<String> result = new ArrayList<>();

        while (k-- > 0)
            result.add(pq.poll().word());

        return result;
    }

    public static void main(String[] args) {
        System.out.println(topKFreqWords(new String[]{"a", "aa", "aaa"}, 1));
    }
}
