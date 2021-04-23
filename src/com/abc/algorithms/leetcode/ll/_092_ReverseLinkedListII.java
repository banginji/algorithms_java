package com.abc.algorithms.leetcode.ll;

import java.util.Stack;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _092_ReverseLinkedListII {
    private static ListNode reverse(ListNode head, int left, int right) {
        Stack<ListNode> stack = new Stack<>();
        ListNode itrNode = new ListNode(-1043, head), leftNode = null, rightNode = null;
        int count = 0;
        while (itrNode != null) {
            if (count + 1 == left) leftNode = itrNode;
            if (count == left) {
                while (count <= right) {
                    stack.push(itrNode);
                    itrNode = itrNode.next;
                    count++;
                }
                rightNode = itrNode;
                break;
            }
            itrNode = itrNode.next;
            count++;
        }
        ListNode partition = new ListNode(-1024, null);
        ListNode itrPartition = partition;
        while (stack.size() > 0) {
            itrPartition.next = stack.pop();
            itrPartition = itrPartition.next;
        }
        leftNode.next = partition.next;
        itrPartition.next = rightNode;
        return leftNode.getVal() == -1043 ? leftNode.next : leftNode;
    }

    public static void main(String[] args) {
        ListNode node = createList(new int[]{1, 2, 3, 4, 5, 6, 7});
        printList(reverse(node, 2, 5));
    }
}
