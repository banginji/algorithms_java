package com.abc.algorithms.leetcode.ll;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _445_AddTwoNumbersII {
    private static ListNode add(ListNode l1, ListNode l2) {
        ListNode rl1 = reverse(l1)[1];
        ListNode rl2 = reverse(l2)[1];
        ListNode res = new ListNode(-1111, null);
        ListNode itrRes = res;
        int carry = 0;
        while (rl1 != null && rl2 != null) {
            int calcValue = rl1.getVal() + rl2.getVal() + carry;
            carry = calcValue / 10;
            itrRes.next = new ListNode(calcValue % 10, null);
            itrRes = itrRes.next;
            rl1 = rl1.next;
            rl2 = rl2.next;
        }
        ListNode nonNullNode = rl1 != null ? rl1 : rl2;
        while (nonNullNode != null) {
            int calcValue = nonNullNode.getVal() + carry;
            carry = calcValue / 10;
            itrRes.next = new ListNode(calcValue % 10, null);
            itrRes = itrRes.next;
            nonNullNode = nonNullNode.next;
        }
        return reverse(res.next)[1];
    }

    private static ListNode[] reverse(ListNode node) {
        if (node.next == null) return new ListNode[]{node, node};
        ListNode[] res = reverse(node.next);
        res[0].next = node;
        node.next = null;
        return new ListNode[]{node, res[1]};
    }

    public static void main(String[] args) {
        printList(add(createList(new int[]{7, 2, 4, 3}), createList(new int[]{5, 6, 4})));
    }
}
