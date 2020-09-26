package com.abc.algorithms.leetcode.hashtable;

import java.util.*;

public class _676_MagicDictionary {
    private record MagicDictionary(Map<Integer, List<String>> dictMap) {
        MagicDictionary() {
            this(new HashMap<>());
        }

        public void buildDict(String[] dictionary) {
            for (String word : dictionary)
                this.dictMap().computeIfAbsent(word.length(), x -> new ArrayList<>()).add(word);
        }

        public boolean search(String searchWord) {
            List<String> possibleMatches = this.dictMap().getOrDefault(searchWord.length(), Collections.emptyList());

            if (possibleMatches.size() == 0) return false;

            int allowedInfractions = 1;

            for (String possibleMatch : possibleMatches)
                for (int idx = 0; idx < possibleMatch.toCharArray().length; idx++) {
                    if (allowedInfractions < 0) return false;
                    if (possibleMatch.charAt(idx) != searchWord.charAt(idx)) allowedInfractions--;
                }

            return true;
        }
    }

    public static void main(String[] args) {
        MagicDictionary dictionary = new MagicDictionary();
        dictionary.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(dictionary.search("leetcode"));
        System.out.println(dictionary.search("hpllo"));
    }
}
