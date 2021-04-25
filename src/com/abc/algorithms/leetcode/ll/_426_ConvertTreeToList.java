package com.abc.algorithms.leetcode.ll;

public class _426_ConvertTreeToList {
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private static Node convert(Node root) {
        if (root == null) return null;
        Node head = new Node(-1000, null, null);
        Node lastNode = r(root, head);
        head.right.left = lastNode;
        lastNode.right = head.right;
        return head.right;
    }
    private static Node r(Node node, Node dl) {
        if (node == null) return dl;
        dl = r(node.left, dl);
        dl.right = new Node(node.val, dl, null);
        return r(node.right, dl.right);
    }
    public static void main(String[] args) {
        Node root = new Node(
                4,
                new Node(
                        2,
                        new Node(1, null, null),
                        new Node(3, null, null)
                ),
                new Node(5, null, null)
        );
        convert(root);
    }
}
