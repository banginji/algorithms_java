package com.abc.algorithms.leetcode.string;

public class _151_ReverseWords {
    private static String reverseWords(String s) {
        String[] words = s.split("[\\s]+");

        StringBuilder sb = new StringBuilder();

        for (int idx = words.length - 1; idx >= 0; idx--) {
            sb.append(words[idx]);
            if (idx > 0) sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("  hello world!  "));
    }
}
