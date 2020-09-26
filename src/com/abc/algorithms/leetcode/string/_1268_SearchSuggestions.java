package com.abc.algorithms.leetcode.string;

import com.abc.algorithms.datastructure.Trie;
import com.abc.algorithms.datastructure.Trie.TrieNode;

import java.util.*;

public class _1268_SearchSuggestions {
    private static List<List<String>> searchSuggestions(String[] products, String searchWord) {
        TrieNode startNode = createWordTree(products);

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

    private static List<String> findAtMostThreeWords(TrieNode node, String prefix) {
        List<String> results = new ArrayList<>();

        if (node.children().containsKey('*'))
            results.add(prefix);

        if (results.size() < 3)
            for (TrieNode childNode : node.children().values())
                if (childNode.charValue() != '*')
                    results.addAll(
                            findAtMostThreeWords(
                                    childNode,
                                    prefix + childNode.charValue()
                            )
                    );

        return results.size() > 3 ? results.subList(0, 3) : results;
    }

    private static TrieNode createWordTree(String[] words) {
        Trie trie = new Trie(new TrieNode('*'));
        for (String word : words)
            trie.insertWord(word);
        return trie.root();
    }

    public static void main(String[] args) {
        System.out.println(searchSuggestions(new String[]{"havana"}, "havana"));
    }
}
