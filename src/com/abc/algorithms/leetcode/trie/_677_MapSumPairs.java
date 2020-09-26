package com.abc.algorithms.leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _677_MapSumPairs {
    private record MapSumTrie(MapSumTrieNode root) {
        private static record MapSumTrieNode(Character charValue, List<Integer> values,
                                             HashMap<Character, MapSumTrieNode> children) {
            MapSumTrieNode(Character charValue, Integer value) {
                this(charValue, new ArrayList<>(), new HashMap<>());
                this.values().add(value);
            }
        }

        public void insertWord(String key, Integer value) {
            MapSumTrieNode currentNode = this.root().children().getOrDefault(key.charAt(0), this.root());

            int currentIdx = currentNode.charValue() == '*' ? 0 : 1;

            while (currentIdx < key.length()) {
                Character currentChar = key.charAt(currentIdx);
                if (currentNode.children().containsKey(currentChar)) {
                    currentNode.values().add(value);
                    currentNode = currentNode.children().get(currentChar);
                    currentIdx++;
                    continue;
                }
                break;
            }

            while (currentIdx < key.length()) {
                Character currentChar = key.charAt(currentIdx++);

                MapSumTrieNode newNode = new MapSumTrieNode(currentChar, value);
                currentNode.children().put(currentChar, newNode);

                currentNode = newNode;
            }

            currentNode.children().put('*', new MapSumTrieNode('*', value));
        }
    }

    private record MapSum(MapSumTrie trie) {
        public void insert(String key, int value) {
            this.trie().insertWord(key, value);
        }

        public int sum(String prefix) {
            MapSumTrie.MapSumTrieNode currentNode = this.trie().root();

            for (Character character : prefix.toCharArray())
                if (currentNode.children().containsKey(character))
                    currentNode = currentNode.children().get(character);

            return currentNode.values().stream().mapToInt(Integer::intValue).sum();
        }
    }

    public static void main(String[] args) {
        MapSumTrie trie = new MapSumTrie(new MapSumTrie.MapSumTrieNode('*', Integer.MAX_VALUE));
        MapSum mapSum = new MapSum(trie);
        mapSum.insert("apple", 3);
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }
}
