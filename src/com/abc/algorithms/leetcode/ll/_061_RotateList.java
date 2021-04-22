package com.abc.algorithms.leetcode.ll;

import java.util.stream.IntStream;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _061_RotateList {
    private static ListNode rotateList(ListNode head, int k) {
        ListNode oldTail = head;
        int size = 1;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            size++;
        }
        int r = size - k % size;
        ListNode newTail = head;
        while (--r > 0) newTail = newTail.next;
        ListNode newHead = newTail.next;
        oldTail.next = head;
        newTail.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node = createList(IntStream.rangeClosed(1, 7).boxed().mapToInt(Integer::intValue).toArray());
        ListNode res = rotateList(node, 3);
        printList(res);
    }
}
