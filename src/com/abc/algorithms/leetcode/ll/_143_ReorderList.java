package com.abc.algorithms.leetcode.ll;

import java.util.Stack;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _143_ReorderList {
    private static void reorder(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode itrSlow = slow;
        while (itrSlow != null) {
            stack.push(itrSlow);
            itrSlow = itrSlow.next;
        }
        ListNode itrNode = head;
        while (stack.size() > 0) {
            ListNode currentNode = itrNode;
            ListNode nextNode = itrNode.next;
            currentNode.next = stack.pop();
            currentNode.next.next = nextNode;
            itrNode = nextNode;
        }
        // Important to terminate the next node to avoid cycle
        itrNode.next.next = null;
    }
    public static void main(String[] args) {
        ListNode node = createList(new int[]{1, 2, 3, 4, 5, 6, 7});
        reorder(node);
        printList(node);
    }
}
