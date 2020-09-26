package com.abc.algorithms.leetcode.trie;

import com.abc.algorithms.datastructure.Trie;

public class _1032_StreamOfChars {
    private record StreamChecker(Trie trie, StringBuilder sb, String[] words) {
        StreamChecker(String[] words) {
            this(new Trie(new Trie.TrieNode('*')), new StringBuilder(), words);
            for (String word : words) {
                StringBuilder sb = new StringBuilder();
                for (int idx = word.length() - 1; idx >= 0; idx--) sb.append(word.charAt(idx));
                insertWord(sb.toString());
            }
        }

        private void insertWord(String word) {
            this.trie().insertWord(word);
        }

        public boolean query(Character letter) {
            this.sb().append(letter);
            Trie.TrieNode currentNode = this.trie().root();

            String query = this.sb().toString();

            for (int idx = sb().length() - 1; idx >= 0; idx--)
                if (currentNode.children().containsKey(query.charAt(idx))) {
                    currentNode = currentNode.children().get(query.charAt(idx));
                    // Detect if end of word and return true if so
                    if (currentNode.children().containsKey('*'))
                        return true;
                } else
                    return false;
            return false;
        }
    }

    public static void main(String[] args) {
        StreamChecker sc = new StreamChecker(new String[]{"cd", "f", "kl"});
        System.out.println(sc.query('a'));
        System.out.println(sc.query('b'));
        System.out.println(sc.query('c'));
        System.out.println(sc.query('d'));
        System.out.println(sc.query('e'));
        System.out.println(sc.query('f'));
        System.out.println(sc.query('g'));
        System.out.println(sc.query('h'));
        System.out.println(sc.query('i'));
        System.out.println(sc.query('j'));
        System.out.println(sc.query('k'));
        System.out.println(sc.query('l'));
        System.out.println(sc.query('m'));
    }
}
