package com.abc.algorithms.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _1371_EvenVowels {
    // https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/discuss/534210/Dew-It-or-Simple-illustration-for-THE-trick
    private static int evenVowels(String s) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int[] pow = {1, 2, 4, 8, 16};

        char[] arr = s.toCharArray();
        int n = arr.length, code = 0, ans = 0;
        boolean[] odd = new boolean[5];

        HashMap<Integer, Integer> encoded = new HashMap<>();
        encoded.put(code, -1);

        for (int wordCharIdx = 0; wordCharIdx < n; ++wordCharIdx) {
            for (int vowelIdx = 0; vowelIdx < 5; ++vowelIdx) {
                if (arr[wordCharIdx] == vowels[vowelIdx]) {
                    code += (odd[vowelIdx] ? -1 : 1) * pow[vowelIdx];
                    odd[vowelIdx] = !odd[vowelIdx];
                    break;
                }
            }
            encoded.putIfAbsent(code, wordCharIdx);
            ans = Math.max(wordCharIdx - encoded.get(code), ans);
        }

        return ans;
    }

    private static int evenVowelsWithBit(String s) {
        int ans = 0, code = 0;

        Map<Integer, Integer> encodedMap = new HashMap<>();
        encodedMap.put(0, -1);

        for (int charIdx = 0; charIdx < s.length(); charIdx++) {
            code ^= 1 << ("aeiou".indexOf(s.charAt(charIdx)) + 1) >> 1;
            encodedMap.putIfAbsent(code, charIdx);
            ans = Math.max(charIdx - encodedMap.get(code), ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(evenVowelsWithBit("yleaeibaisf"));
    }
}
