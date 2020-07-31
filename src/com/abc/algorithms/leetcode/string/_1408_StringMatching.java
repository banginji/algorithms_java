package com.abc.algorithms.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _1408_StringMatching {
    private static List<String> stringMatching(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length));

        List<String> result = new ArrayList<>();

        for (int iIdx = 0; iIdx < words.length; iIdx++) {
            for (int jIdx = iIdx + 1; jIdx < words.length; jIdx++) {
                if (words[jIdx].contains(words[iIdx])) {
                    result.add(words[iIdx]);
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(stringMatching(new String[]{"mass", "as", "hero", "superhero"}));
    }
}
