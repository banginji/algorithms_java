package com.abc.algorithms.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class _1367_LinkedListInBTree {

    private record ListNode(int val, ListNode next) {
    }

    private static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private static boolean hasPath(ListNode head, TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (queue.size() > 0) {
            TreeNode currentNode = queue.poll();

            if (checkNode(currentNode, head))
                return true;

            if (currentNode.left != null) queue.offer(currentNode.left);
            if (currentNode.right != null) queue.offer(currentNode.right);
        }

        return false;
    }

    private static boolean checkNode(TreeNode treeNode, ListNode listNode) {
        if (listNode == null)
            return true;
        if (treeNode != null && treeNode.val != null && treeNode.val == listNode.val)
            if (checkNode(treeNode.left, listNode.next))
                return true;
            else return checkNode(treeNode.right, listNode.next);
        return false;
    }

    private static TreeNode createTree(Integer[] nodes) {
        TreeNode[] bTreeArray = new TreeNode[nodes.length];

        TreeNode root = new TreeNode(nodes[0]);
        bTreeArray[0] = root;

        for (int idx = 1; idx < nodes.length; idx += 2) {
            TreeNode parent = bTreeArray[getBTreeParentPosition(idx)];
            parent.left = new TreeNode(nodes[idx]);
            bTreeArray[idx] = parent.left;
            if (idx + 1 < nodes.length) {
                parent.right = new TreeNode(nodes[idx + 1]);
                bTreeArray[idx + 1] = parent.right;
            }
        }

        return root;
    }

    private static int getBTreeParentPosition(int position) {
        if (position == 0)
            return -1;

        return (position - 1) / 2;
    }

    public static void main(String[] args) {
        TreeNode root = createTree(new Integer[]{1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3});
        ListNode head = new ListNode(4, new ListNode(1, new ListNode(8, null)));

        System.out.println(hasPath(head, root));
    }
}
