package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1457_PseudoPalindrome {
    private static void pseudoPalindromePaths(TreeNode node) {
        dfs(node, new ArrayList<>());
    }

    private static int count = 0;

    private static void dfs(TreeNode node, List<TreeNode> nodesInPath) {
        if (node == null || node.val == null)
            return;

        if (CreateBinaryTree.isLeafNode(node)) {
            // Add node to path since its a leaf node and should be added to the list of nodes for calculation
            nodesInPath.add(node);

            // Bool check on each bit. If even numbered integer then result will be true for that integer
            boolean[] palindromeCheckBits = new boolean[10];
            Arrays.fill(palindromeCheckBits, true);
            for (TreeNode nodeInPath : nodesInPath)
                palindromeCheckBits[nodeInPath.val] = !palindromeCheckBits[nodeInPath.val];

            // Check if its a palindrome
            boolean isPalindrome = true;
            // Infractions is for allowing at most one odd numbered integer
            int infractions = 0;
            for (boolean checkBit : palindromeCheckBits) {
                // Check to see if integer is odd and if so check if number of infractions (1) has exceeded
                if (!checkBit && ++infractions <= 1) continue;
                // If odd then its not a palindrome. Exit the loop
                if (!checkBit) {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) count++;

            // Remove leaf node after calculation so that it is not persisted in other tree paths
            nodesInPath.remove(node);
            return;
        }

        // Add current node to node path list for back tracking
        nodesInPath.add(node);
        dfs(node.left, nodesInPath);
        dfs(node.right, nodesInPath);
        // Remove node after path evaluation so that it is not persisted in other tree paths
        nodesInPath.remove(node);
    }

    private static int pp(TreeNode root) {
        boolean[] oddNums = new boolean[10];
        return dfs(root, oddNums, 0);
    }

    private static int dfs(TreeNode node, boolean[] oddNums, int count) {
        if (node == null || node.val == null) return count;
        oddNums[node.val] = !oddNums[node.val];
        if (node.left == null && node.right == null) if (isPalindrome(oddNums)) count++;
        count = dfs(node.left, oddNums, count);
        count = dfs(node.right, oddNums, count);
        oddNums[node.val] = !oddNums[node.val];
        return count;
    }

    private static boolean isPalindrome(boolean[] oddNums) {
        int allowedInfractions = 1;
        for (boolean isOdd : oddNums) {
            if (isOdd && allowedInfractions > 0) {
                allowedInfractions--;
                continue;
            }
            if (!isOdd) continue;
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CreateBinaryTree createBinaryTree = new CreateBinaryTree();
        TreeNode root = createBinaryTree.createTree(new Integer[]{2, 3, 1, 3, 1, null, 1});
        System.out.println(pp(root));
    }
}
