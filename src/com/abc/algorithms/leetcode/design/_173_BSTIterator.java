package com.abc.algorithms.leetcode.design;

import java.util.PriorityQueue;

public class _173_BSTIterator {
    private record TreeNode(int val, TreeNode left, TreeNode right) {
        TreeNode(int val) {
            this(val, null, null);
        }
    }

    class BSTIterator {
        TreeNode root;
        private final PriorityQueue<Integer> pq = new PriorityQueue<>();

        public BSTIterator(TreeNode root) {
            this.root = root;
            inOrderTraversal(this.root);
        }

        private void inOrderTraversal(TreeNode node) {
            if (node == null) return;
            inOrderTraversal(node.left);
            this.pq.offer(node.val);
            inOrderTraversal(node.right);
        }

        public int next() {
            if (pq.size() > 0)
                return pq.poll();
            return -1;
        }

        public boolean hasNext() {
            return pq.size() > 0;
        }
    }
}
