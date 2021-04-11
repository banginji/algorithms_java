package com.abc.algorithms.leetcode.uncategorized;

import com.abc.algorithms.datastructure.BinarySearchTree;

public class _701_BSTInsert {
    private static void insert(BinarySearchTree<Integer> bst, int val) {
        bst.insert(val);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        insert(bst, 10);
    }
}
