package com.abc.algorithms.leetcode.uncategorized;

import com.abc.algorithms.datastructure.BinarySearchTree;

public class _700_SearchBST {
    private static BinarySearchTree.Node<Integer> search(BinarySearchTree<Integer> bst, int val) {
        return bst.search(val);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(20);
        bst.insert(100);
        bst.insert(150);
        bst.insert(115);
        bst.insert(119);
        System.out.println(search(bst, 150));
    }
}
