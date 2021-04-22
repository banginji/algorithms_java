package com.abc.algorithms.leetcode.ll;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _086_PartitionList {
    private static ListNode partitionList(ListNode node, int x) {
        ListNode head = new ListNode(-1200, null);
        ListNode itrHead = head;
        ListNode midPivot = new ListNode(-1100, node);
        ListNode itrMidPivot = midPivot;
        while (itrMidPivot.next != null) {
            if (itrMidPivot.next.getVal() < x) {
                itrHead.next = itrMidPivot.next;
                itrHead = itrHead.next;
                itrMidPivot.next = itrMidPivot.next.next;
                continue;
            }
            itrMidPivot = itrMidPivot.next;
        }
        itrHead.next = midPivot.next;
        return head.next;
    }
    public static void main(String[] args) {
        ListNode node = createList(new int[]{1, 4, 3, 2, 5, 2});
        printList(partitionList(node, 3));
    }
}
