package com.abc.algorithms.leetcode.uncategorized;

public class _383_RansomNote {
    private static boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];

        for (char m : magazine.toCharArray())
            chars[m - 'a']++;
        for (char r : ransomNote.toCharArray())
            if (--chars[r - 'a'] < 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                canConstruct(
                        "bjaajgea",
                        "affhiiicabhbdchbidghccijjbfjfhjeddgggbajhidhjchiedhdibgeaecffbbbefiabjdhggihccec"
                )
        );
    }
}
