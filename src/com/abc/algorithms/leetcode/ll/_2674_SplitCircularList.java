package com.abc.algorithms.leetcode.ll;

import com.abc.algorithms.leetcode.ll.LinkedListHelper.ListNode;

import java.util.HashSet;
import java.util.Set;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.createList;

public class _2674_SplitCircularList {
    private static ListNode[] split(ListNode node) {
        Set<ListNode> set = new HashSet<>();

        ListNode currentNode = node;

        int length = 0;

        while (!set.contains(currentNode)) {
            set.add(currentNode);
            currentNode = currentNode.next;
            length++;
        }

        double first = Math.ceil((double)length/2);

        ListNode itrNode = node;

        int itrCount = 0;

        ListNode[] res = new ListNode[2];
        res[0] = node;
        while (itrCount < length) {
            if (itrCount == first) {
                res[1] = itrNode.next;
                break;
            }
            itrNode = itrNode.next;
            itrCount++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        ListNode head = createList(nums);
        ListNode tail;
        ListNode itrNode = head;
        while (itrNode.next != null) itrNode = itrNode.next;
        tail = itrNode;
        tail.next = head;
        ListNode[] res = split(head);
        System.out.println();
    }
}
