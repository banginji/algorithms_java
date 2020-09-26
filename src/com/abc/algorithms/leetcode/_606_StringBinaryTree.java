package com.abc.algorithms.leetcode;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree;
import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

public class _606_StringBinaryTree {
    private static String tree2String(TreeNode t) {
        StringBuilder stringBuilder = new StringBuilder();
        inOrderTraversal(t, stringBuilder);

        return stringBuilder.toString();
    }

    private static void inOrderTraversal(TreeNode node, StringBuilder stringBuilder) {
        if (node != null && node.val != null)
            stringBuilder.append(node.val);
        else
            return;

        if (CreateBinaryTree.isLeafNode(node))
            return;

        stringBuilder.append("(");
        inOrderTraversal(node.left, stringBuilder);
        stringBuilder.append(")");
        stringBuilder.append("(");
        inOrderTraversal(node.right, stringBuilder);
        stringBuilder.append(")");
    }

    public static void main(String[] args) {
        CreateBinaryTree createBinaryTree = new CreateBinaryTree();
        TreeNode root = createBinaryTree.createTree(new Integer[]{1, 2, 3, null, 4});
        System.out.println(tree2String(root));
    }
}
