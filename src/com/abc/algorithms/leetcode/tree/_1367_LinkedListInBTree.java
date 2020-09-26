package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _1367_LinkedListInBTree {
    public static record ListNode(int val, ListNode next) {
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
        if (treeNode != null && treeNode.val != null && treeNode.val == listNode.val())
            if (checkNode(treeNode.left, listNode.next()))
                return true;
            else return checkNode(treeNode.right, listNode.next());
        return false;
    }

    public static void main(String[] args) {
        CreateBinaryTree createBinaryTree = new CreateBinaryTree();
        TreeNode root = createBinaryTree.createTree(new Integer[]{1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3});
        ListNode head = new ListNode(4, new ListNode(1, new ListNode(8, null)));

        System.out.println(hasPath(head, root));
    }
}
