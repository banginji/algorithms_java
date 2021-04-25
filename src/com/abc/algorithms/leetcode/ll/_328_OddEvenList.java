package com.abc.algorithms.leetcode.ll;

import java.util.ArrayDeque;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _328_OddEvenList {
    private static void oddEvenList(ListNode head) {
        r(head, new ArrayDeque<>());
    }
    private static void r(ListNode node, ArrayDeque<ListNode> evenNodes) {
        if (node.next == null) {
            node.next = evenNodes.peekLast();
            return;
        }
        ListNode nextNode = new ListNode(node.next.getVal(), null);
        if (evenNodes.size() > 0) evenNodes.peekFirst().next = nextNode;
        evenNodes.offerFirst(nextNode);
        node.next = node.next.next;
        if (node.next != null)
            r(node.next, evenNodes);
        else
            node.next = evenNodes.peekLast();
    }
    public static void main(String[] args) {
        ListNode node = createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        oddEvenList(node);
        printList(node);
    }
}
