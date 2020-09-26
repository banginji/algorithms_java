package com.abc.algorithms.datastructure;

public class BinaryTree {
    public static record TreeNode<T>(T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
    }
}
