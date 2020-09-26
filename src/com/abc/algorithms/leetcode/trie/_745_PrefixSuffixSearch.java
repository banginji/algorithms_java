package com.abc.algorithms.leetcode.trie;

import java.util.*;

public class _745_PrefixSuffixSearch {
    // A unique trie node for managing the indices of the words inserted
    private record WordFilterTrie(WordFilterTrieNode root) {
        private static record WordFilterTrieNode(Character charValue, Set<Integer> indices,
                                                 Map<Character, WordFilterTrieNode> children) {
            private WordFilterTrieNode(Character charValue) {
                this(charValue, new HashSet<>(), new HashMap<>());
            }

            private WordFilterTrieNode(Character charValue, int idx) {
                this(charValue, new HashSet<>(), new HashMap<>());
                this.indices().add(idx);
            }
        }

        private void insertWord(String word, int idx) {
            WordFilterTrieNode startNode = this.root().children().getOrDefault(word.charAt(0), this.root());
            startNode.indices().add(idx);

            int currentIdx = startNode.charValue() == '*' ? 0 : 1;

            while (currentIdx < word.length()) {
                Character currentChar = word.charAt(currentIdx);
                if (startNode.children().containsKey(currentChar)) {
                    startNode = startNode.children().get(currentChar);
                    startNode.indices().add(idx);
                    currentIdx++;
                    continue;
                }
                break;
            }

            WordFilterTrieNode currentNode = startNode;

            while (currentIdx < word.length()) {
                Character currentChar = word.charAt(currentIdx++);

                WordFilterTrieNode newTrieNode = new WordFilterTrieNode(currentChar, idx);
                currentNode.children().put(currentChar, newTrieNode);
                currentNode.indices().add(idx);

                currentNode = newTrieNode;
            }

            currentNode.children().put('*', new WordFilterTrieNode('*'));
        }
    }

    private record WordFilter(String[] words, WordFilterTrie trie) {
        public WordFilter(String[] words) {
            this(words, new WordFilterTrie(new WordFilterTrie.WordFilterTrieNode('*')));
            for (int idx = 0; idx < words().length; idx++) {
                int lastIdx = words[idx].length() - 1;
                while (lastIdx >= 0) {
                    insertWord(words[idx].substring(lastIdx--) + "#" + words[idx], idx);
                }
            }
        }

        private Set<Integer> f(String prefix, String suffix) {
            WordFilterTrie.WordFilterTrieNode root = this.trie().root();

            WordFilterTrie.WordFilterTrieNode currentNode = searchChar(suffix, root);

            if (currentNode == null) return Set.of(-1);

            // Get the next node which should be the delimiter '#'
            currentNode = currentNode.children().get('#');

            currentNode = searchChar(prefix, currentNode);

            return currentNode == null ? Set.of(-1) : currentNode.indices();
        }

        private WordFilterTrie.WordFilterTrieNode searchChar(String prefixOrSuffix, WordFilterTrie.WordFilterTrieNode node) {
            if (prefixOrSuffix.length() == 0)
                return node;
            /**
             * If a child contains the first char in the prefix / suffix then get the
             * substring of prefix / suffix and continue with recursion
             */
            Character firstChar = prefixOrSuffix.charAt(0);
            if (node.children().containsKey(firstChar))
                return searchChar(prefixOrSuffix.substring(1), node.children().get(firstChar));
            return null;
        }

        private void insertWord(String word, int weight) {
            this.trie().insertWord(word, weight);
        }
    }

    public static void main(String[] args) {
        WordFilter filter = new WordFilter(new String[]{"abc", "pqr", "ahjwfiunechbicc", "adc"});
        System.out.println(filter.f("a", "c"));
    }
}
