package com.abc.algorithms.leetcode.trie;

import com.abc.algorithms.datastructure.Trie;
import com.abc.algorithms.datastructure.Trie.TrieNode;

import java.util.ArrayList;
import java.util.List;

public class _1023_CamelCaseMatch {
    private static List<Boolean> camelCaseMatch(String[] queries, String pattern) {
        TrieNode root = createPatternNodes(pattern);

        List<Boolean> results = new ArrayList<>();

        for (String query : queries)
            results.add(checkQuery(query, root));

        return results;
    }

    private static boolean checkQuery(String query, TrieNode startNode) {
        int queryIdx = 0;

        while (queryIdx < query.length()) {
            if (startNode.children().containsKey(query.charAt(queryIdx))) {
                startNode = startNode.children().get(query.charAt(queryIdx++));
                continue;
            }

            if (startNode.children().containsKey('*'))
                break;

            /**
             * Not in the pattern Trie
             * Not at the end of the patterns Trie
             * Hence if we encounter an uppercase letter in the word return false
             */
            if (Character.isUpperCase(query.charAt(queryIdx)))
                return false;

            queryIdx++;
        }

        // After pattern is all matched up if there are caps then return false
        for (int idx = queryIdx; idx < query.length(); idx++)
            if (Character.isUpperCase(query.charAt(idx)))
                return false;

        // If pattern is not at the end then return false
        return !startNode.children().keySet().stream().allMatch(c -> c != '*');
    }

    private static TrieNode createPatternNodes(String pattern) {
        Trie trie = new Trie(new TrieNode('*'));
        trie.insertWord(pattern);
        return trie.root();
    }

    public static void main(String[] args) {
        System.out.println(
                camelCaseMatch(
                        new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"},
                        "FoBa"
                )
        );
    }
}
