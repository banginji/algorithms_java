package com.abc.algorithms.datastructure;

public class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(T data) {
        if (this.root == null) {
            this.root = new Node<>(data);
            return;
        }
        insert(getRoot(), data);
    }

    private void insert(Node<T> node, T data) {
        if (node.data.compareTo(data) > 0) {
            if (node.leftChild == null)
                node.leftChild = new Node<>(data);
            else
                insert(node.leftChild, data);
        } else {
            if (node.rightChild == null)
                node.rightChild = new Node<>(data);
            else
                insert(node.rightChild, data);
        }
    }

    public Node<T> search(T data) {
        return search(getRoot(), data);
    }

    private Node<T> search(Node<T> node, T data) {
        if (node.data.compareTo(data) > 0) {
            if (node.leftChild != null) {
                if (node.leftChild.data.compareTo(data) == 0)
                    return node.leftChild;
                else
                    return search(node.leftChild, data);
            } else
                return null;
        } else {
            if (node.rightChild != null) {
                if (node.rightChild.data.compareTo(data) == 0)
                    return node.rightChild;
                else
                    return search(node.rightChild, data);
            } else
                return null;
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public static class Node<T> {
        T data;
        Node<T> leftChild;
        Node<T> rightChild;

        public Node(T data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }

        @Override
        public String toString() {
            return "\nNode{" +
                    "data=" + data +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    "}\n";
        }
    }
}
