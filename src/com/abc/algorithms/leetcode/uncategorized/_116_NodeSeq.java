package com.abc.algorithms.leetcode.uncategorized;

import java.util.LinkedList;
import java.util.Queue;

public class _116_NodeSeq {
    private static void connect(Node<Integer> root) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (queue.size() > 0) {
            Node<Integer> currentNode = queue.remove();
            if (currentNode != null && currentNode.left != null)
                queue.add(currentNode.left);
            if (currentNode != null && currentNode.right != null)
                queue.add(currentNode.right);
            if (queue.peek() == null) {
                currentNode.next = null;
                queue.remove();
                queue.add(null);
            } else
                currentNode.next = queue.peek();
        }
    }

    static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;
        Node<T> next;

        Node(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> three = new Node<>(3);
        one.left = two;
        one.right = three;

        Node<Integer> four = new Node<>(4);
        Node<Integer> five = new Node<>(5);
        two.left = four;
        two.right = five;

        Node<Integer> six = new Node<>(6);
        Node<Integer> seven = new Node<>(7);
        three.left = six;
        three.right = seven;

        connect(one);

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(one);

        while (queue.size() > 0) {
            Node<Integer> currentNode = queue.remove();
            System.out.print(currentNode.val);
            queue.add(currentNode.left);
            queue.add(currentNode.right);
        }
    }
}
