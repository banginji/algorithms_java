package com.abc.algorithms.leetcode.string;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class _824_GoatLatin {
    private static String goatLatin(String s) {
        List<String> words = Arrays.stream(s.split("[\\W\\s]+")).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < words.size(); idx++) {
            String word = words.get(idx);
            if (wordStartsWithAVowel(word)) {
                sb.append(word.substring(1)).append("ma").append("a".repeat(idx+1));
            } else {
                sb.append(word.substring(1)).append(word.charAt(0)).append("ma").append("a".repeat(idx+1));
            }
            sb.append(" ");
        }

        return sb.toString();
    }

    private static boolean wordStartsWithAVowel(String word) {
        return Set.of('a', 'e', 'i', 'o', 'u').stream().anyMatch(character -> character.equals(word.toLowerCase().charAt(0)));
    }

    public static void main(String[] args) {
        System.out.println(goatLatin("The quick brown fox jumped over the lazy dog"));
        System.out.println(wordStartsWithAVowel("bc"));
    }
}
