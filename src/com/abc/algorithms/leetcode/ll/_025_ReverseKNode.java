package com.abc.algorithms.leetcode.ll;

import java.util.ArrayDeque;
import java.util.stream.IntStream;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _025_ReverseKNode {
    private static ListNode reverseKNodes(ListNode head, int k) {
        ListNode itrNode = head;
        int size = 0;
        while (itrNode != null) {
            size++;
            itrNode = itrNode.getNext();
        }
        return r(head, k, size);
    }
    private static ListNode r(ListNode node, int k, int remSize) {
        if (node == null) return null;
        if (remSize < k) return node;
        ArrayDeque<ListNode> deque = new ArrayDeque<>();
        int count = 0;
        while (++count <= k && node != null) {
            remSize--;
            deque.offerFirst(node);
            node = node.getNext();
        }
        ListNode resNode = deque.pollFirst();
        resNode.next = null;
        ListNode res = resNode;
        while (deque.size() > 0) {
            resNode.next = deque.pollFirst();
            resNode = resNode.next;
            resNode.next = null;
        }
        resNode.next = r(node, k, remSize);
        return res;
    }
    public static void main(String[] args) {
        ListNode node = createList(IntStream.rangeClosed(1, 7).boxed().mapToInt(Integer::intValue).toArray());
        ListNode res = reverseKNodes(node, 3);
        printList(res);
    }
}
