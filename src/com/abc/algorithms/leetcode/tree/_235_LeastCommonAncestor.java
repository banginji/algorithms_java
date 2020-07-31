package com.abc.algorithms.leetcode.tree;

public class _235_LeastCommonAncestor {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }
    }

    private static TreeNode[] createGraph() {
        TreeNode rootNode = new TreeNode(6);

        TreeNode oneNode = new TreeNode(2);
        TreeNode twoNode = new TreeNode(8);

        TreeNode threeNode = new TreeNode(0);
        TreeNode fourNode = new TreeNode(4);
        TreeNode fiveNode = new TreeNode(7);
        TreeNode sixNode = new TreeNode(9);

        TreeNode nineNode = new TreeNode(3);
        TreeNode tenNode = new TreeNode(5);

        sixNode.left = nineNode;
        sixNode.right = tenNode;

        oneNode.left = threeNode;
        oneNode.right = fourNode;

        twoNode.left = fiveNode;
        twoNode.right = sixNode;

        rootNode.left = oneNode;
        rootNode.right = twoNode;

        return new TreeNode[]{rootNode, oneNode, fourNode};
    }

    private static TreeNode leastCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = root;

        while (lca.left != null || lca.right != null) {
            if (p.val < lca.val && q.val < lca.val) {
                lca = lca.left;
                continue;
            }

            if (p.val > lca.val && q.val > lca.val) {
                lca = lca.right;
                continue;
            }

            return lca;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode[] nodes = createGraph();

        System.out.println(leastCommonAncestor(nodes[0], nodes[1], nodes[2]).val);
    }
}
