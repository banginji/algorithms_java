package com.abc.algorithms.leetcode.string;

import com.abc.algorithms.datastructure.Trie;

import java.util.*;

public class _1268_SearchSuggestions {
    private static List<List<String>> searchSuggestions(String[] products, String searchWord) {
        Trie.TrieNode startNode = createWordTree(products);

        List<List<String>> results = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (Character searchWordChar : searchWord.toCharArray()) {
            if (startNode.children().containsKey(searchWordChar)) {
                results.add(
                        findAtMostThreeWords(
                                startNode.children().get(searchWordChar),
                                sb.append(searchWordChar).toString()
                        )
                );
                startNode = startNode.children().get(searchWordChar);
            }
        }

        return results;
    }

    private static List<String> findAtMostThreeWords(Trie.TrieNode node, String prefix) {
        List<String> results = new ArrayList<>();

        if (node.children().containsKey('*'))
            results.add(prefix);

        if (results.size() < 3)
            for (Trie.TrieNode childNode : node.children().values())
                if (childNode.c() != '*')
                    results.addAll(
                            findAtMostThreeWords(
                                    childNode,
                                    prefix + childNode.c()
                            )
                    );

        return results.size() > 3 ? results.subList(0, 3) : results;
    }

    private static Trie.TrieNode createWordTree(String[] words) {
        Trie trie = new Trie(new Trie.TrieNode('*'));
        for (String word : words)
            trie.insertWord(word);
        return trie.root();
    }

    public static void main(String[] args) {
        System.out.println(searchSuggestions(new String[]{"havana"}, "havana"));
    }
}
