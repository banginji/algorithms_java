package com.abc.algorithms.leetcode.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _273_IntegerToWords {
    private static String integerToWords(int num) {
        // Billion, Million, Thousand
        // Hundred

        LinkedList<String> words = new LinkedList<>();
        words.add("Thousand");
        words.add("Million");
        words.add("Billion");

        Map<Integer, String> oneToTenMap = Map.of(
                1, "One", 2, "Two", 3, "Three", 4, "Four", 5, "Five", 6, "Six", 7, "Seven", 8, "Eight", 9, "Nine", 10, "Ten"
        );
        Map<Integer, String> tenToTwentyMap = Map.of(
                11, "Eleven", 12, "Twelve", 13, "Thirteen", 14, "Fourteen", 15, "Fifteen", 16, "Sixteen", 17, "Seventeen", 18, "Eighteen", 19, "Nineteen", 20, "Twenty"
        );
        Map<Integer, String> tensMap = Map.of(
                30, "Thirty", 40, "Forty", 50, "Fifty", 60, "Sixty", 70, "Seventy", 80, "Eighty", 90, "Ninety"
        );

        Map<Integer, String> wordsMap = new HashMap<>();
        wordsMap.putAll(oneToTenMap);
        wordsMap.putAll(tenToTwentyMap);
        wordsMap.putAll(tensMap);

        int pivot = 0;

        int divisor = 1000;

        while (num / divisor > 0) {
            pivot++;
            divisor *= 1000;
        }
        divisor /= 1000;

        // (1234567 - (1234567 % 1000000)) / 1000000 = 1
        // 1234567 % 1000000 = 234567
        StringBuilder sb = new StringBuilder();

        while (pivot >= 0) {
            int digit = (num - (num % divisor)) / divisor;
            hundredthsPlace(digit, sb, wordsMap);
            num %= divisor;
            if (pivot - 1 >= 0)
                sb.append(words.get(pivot - 1));
            pivot--;
            divisor /= 1000;
        }

        return sb.toString();
    }

    private static void hundredthsPlace(int num, StringBuilder sb, Map<Integer, String> wordsMap) {
        // (234 - (234%100)) / 100 = 2
        if (num / 100 > 0) {
            int digit = (num - (num % 100)) / 100;
            sb.append(wordsMap.get(digit)).append("Hundred");
            tenthsAndOnesPlace(num % 100, sb, wordsMap);
        } else {
            tenthsAndOnesPlace(num, sb, wordsMap);
        }
    }

    private static void tenthsAndOnesPlace(int num, StringBuilder sb, Map<Integer, String> wordsMap) {
        if (wordsMap.containsKey(num))
            sb.append(wordsMap.get(num));
        else {
            // 34 - (34%10)
            if (num / 10 > 0) {
                int tenthsDigit = (num - (num % 10));
                sb.append(wordsMap.get(tenthsDigit));
            }
            // 34 % 10
            if (num % 10 > 0) {
                int onesDigit = num % 10;
                sb.append(wordsMap.get(onesDigit));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(integerToWords(1234567891));
    }
}
