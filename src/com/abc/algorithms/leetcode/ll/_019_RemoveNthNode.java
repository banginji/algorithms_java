package com.abc.algorithms.leetcode.ll;

import java.util.stream.IntStream;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _019_RemoveNthNode {
    private static void removeNthNode(ListNode head, int n) {
        ListNode slow = head, fast = head;
        int count = 0;
        while (++count <= n) fast = fast.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
    }
    public static void main(String[] args) {
        ListNode node = createList(IntStream.rangeClosed(1, 5).toArray());
        removeNthNode(node, 2);
        printList(node);
    }
}
