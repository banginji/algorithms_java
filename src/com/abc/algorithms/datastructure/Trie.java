package com.abc.algorithms.datastructure;

import java.util.HashMap;
import java.util.Map;

public record Trie(TrieNode root) {
    public static record TrieNode(Character charValue, Map<Character, TrieNode> children) {
        public TrieNode(Character c) {
            this(c, new HashMap<>());
        }
    }

    public void insertWord(String word) {
        TrieNode startNode = this.root().children().getOrDefault(word.charAt(0), this.root());

        int currentIdx = startNode.charValue() == '*' ? 0 : 1;

        while (currentIdx < word.length()) {
            Character currentChar = word.charAt(currentIdx);
            if (startNode.children().containsKey(currentChar)) {
                startNode = startNode.children().get(currentChar);
                currentIdx++;
                continue;
            }
            break;
        }

        TrieNode currentNode = startNode;

        while (currentIdx < word.length()) {
            Character currentChar = word.charAt(currentIdx++);

            TrieNode newTrieNode = new TrieNode(currentChar);
            currentNode.children().put(currentChar, newTrieNode);

            currentNode = newTrieNode;
        }

        currentNode.children().put('*', new TrieNode('*'));
    }
}
