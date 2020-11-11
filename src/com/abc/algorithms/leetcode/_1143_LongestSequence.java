package com.abc.algorithms.leetcode;

import java.util.Arrays;

public class _1143_LongestSequence {
    private static int lcsSlow(String str1, String str2) {
        String longer = str1.length() >= str2.length() ? str1 : str2;
        String shorter = str1.length() < str2.length() ? str1 : str2;

        return lcsSlow(longer, 0, shorter, 0, 0);
    }

    private static int lcsSlow(String longer, int longerIdx, String shorter, int shorterIdx, int count) {
        if (longerIdx == longer.length() || shorterIdx == shorter.length())
            return count;

        if (longer.charAt(longerIdx) == shorter.charAt(shorterIdx))
            return lcsSlow(longer, longerIdx + 1, shorter, shorterIdx + 1, count + 1);

        return Math.max(
                lcsSlow(longer, longerIdx + 1, shorter, shorterIdx, count),
                lcsSlow(longer, longerIdx, shorter, shorterIdx + 1, count)
        );
    }

    private static int lcsFast(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        for (int[] d : dp) Arrays.fill(d, Integer.MIN_VALUE);

        return lcsFast(str1.toCharArray(), 0, str2.toCharArray(), 0, dp);
    }

    private static int lcsFast(char[] charArrOne, int idxOne, char[] charArrTwo, int idxTwo, int[][] dp) {
        if (idxOne >= charArrOne.length || idxTwo >= charArrTwo.length) return 0;

        if (dp[idxOne][idxTwo] != Integer.MIN_VALUE) return dp[idxOne][idxTwo];

        if (charArrOne[idxOne] == charArrTwo[idxTwo])
            return dp[idxOne][idxTwo] = 1 + lcsFast(charArrOne, idxOne + 1, charArrTwo, idxTwo + 1, dp);

        return dp[idxOne][idxTwo] = Math.max(
                lcsFast(charArrOne, idxOne + 1, charArrTwo, idxTwo, dp),
                lcsFast(charArrOne, idxOne, charArrTwo, idxTwo + 1, dp)
        );
    }

    /**
     *     b a b a b
     *     0 0 0 0 0
     * a 0 0 1 1 1 1
     * b 0 1 1 2 2 2
     * b 0 1 1 2 2 3
     * b 0 1 1 2 2 3
     * b 0 1 1 2 2 3
     */

    private static int lcsFastMyWay(String str1, String str2) {
        char[] char1Array = str1.toCharArray();
        char[] char2Array = str2.toCharArray();

        int[][] result = new int[char1Array.length + 1][char2Array.length + 1];

        for (int char1Idx = 1; char1Idx <= char1Array.length; char1Idx++) {
            for (int char2Idx = 1; char2Idx <= char2Array.length; char2Idx++) {
                if (char1Array[char1Idx - 1] == char2Array[char2Idx - 1])
                    result[char1Idx][char2Idx] = result[char1Idx - 1][char2Idx - 1] + 1;
                else
                    result[char1Idx][char2Idx] = Math.max(
                            result[char1Idx][char2Idx - 1],
                            result[char1Idx - 1][char2Idx]
                    );
            }
        }

        return result[char1Array.length][char2Array.length];
    }

    public static void main(String[] args) {
        System.out.println(
                lcsFast("abbbba", "babab") == 3
        );

        System.out.println(
                lcsFast("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq") == 6
        );

        System.out.println(
                lcsFastMyWay("abbbba", "babab") == 3
        );

        System.out.println(
                lcsFastMyWay("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq") == 6
        );

        /**
         * Time taken: 1.78706E-4
         * Time taken: 3.251E-5
         * Time taken: 2.8641E-5
         * Time taken: 2.2743E-5
         * Time taken: 2.0842E-5
         * Time taken: 2.5833E-5
         * Time taken: 2.0507E-5
         * Time taken: 2.7137E-5
         * Time taken: 2.4215E-5
         *
         * ~ 25e-6 sec
         */
        int idxOne = 0;
        while (idxOne++ < 9)
            TimeIt.timeTaken(() -> lcsFast("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));

        System.out.println("-".repeat(25));

        /**
         * Time taken: 4.2632E-5
         * Time taken: 4.5178E-5
         * Time taken: 4.4723E-5
         * Time taken: 3.2617E-5
         * Time taken: 4.6875E-5
         * Time taken: 3.3468E-5
         * Time taken: 3.2128E-5
         * Time taken: 3.885E-5
         * Time taken: 3.213E-5
         *
         * ~ 40e-6 sec
         */
        int idxTwo = 0;
        while (idxTwo++ < 9)
            TimeIt.timeTaken(() -> lcsFastMyWay("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));

        System.out.println("-".repeat(25));
    }
}
