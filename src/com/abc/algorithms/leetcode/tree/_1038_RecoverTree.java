package com.abc.algorithms.leetcode.tree;

import com.abc.algorithms.leetcode.tree.CreateBinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _1038_RecoverTree {
    private static TreeNode recoverTree(String S) {
        Queue<int[]> queue = new LinkedList<>();

        int dotCtr = 0;
        int itrIdx = 0;
        while (itrIdx < S.length())
            if (S.charAt(itrIdx) == '-') {
                itrIdx++; dotCtr++;
            }
            else {
                StringBuilder sb = new StringBuilder();
                while (itrIdx < S.length() && S.charAt(itrIdx) != '-') sb.append(S.charAt(itrIdx++));
                queue.offer(new int[]{Integer.parseInt(sb.toString()), dotCtr});
                dotCtr = 0;
            }

        return constructTree(queue, 0);
    }

    private static TreeNode constructTree(Queue<int[]> queue, int lvl) {
        if (queue.size() == 0 || queue.peek()[1] != lvl) return null;

        TreeNode root = new TreeNode(queue.poll()[0]);
        root.left = constructTree(queue, lvl + 1);
        root.right = constructTree(queue, lvl + 1);

        return root;
    }

    public static void main(String[] args) {
        recoverTree("1-2--3--4-5--6--7");
        recoverTree("1-401--349---90--88");
    }
}
