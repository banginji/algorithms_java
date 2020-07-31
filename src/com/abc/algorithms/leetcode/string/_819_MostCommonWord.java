package com.abc.algorithms.leetcode.string;

import java.util.*;
import java.util.stream.Collectors;

public class _819_MostCommonWord {
    private static String mostCommonWord(String paragraph, String[] banned) {
        List<String> words = Arrays.stream(paragraph.split("[\\W\\s]+")).map(String::toLowerCase).collect(Collectors.toList());

        Map<String, Integer> wordMap = new HashMap<>();

        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));

        for (String word : words) {
            if (bannedWords.contains(word)) {
                continue;
            }

            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        return wordMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public static void main(String[] args) {
        System.out.println(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
    }
}
