package com.abc.algorithms.leetcode.ll;

import java.util.Stack;

import static com.abc.algorithms.leetcode.ll.LinkedListHelper.*;

public class _369_PlusOneList {
    private static ListNode plusOneList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode itrNode = head;
        while (itrNode != null) {
            stack.push(itrNode);
            itrNode = itrNode.next;
        }
        int carry = 1;
        ListNode resNode = null;
        while (stack.size() > 0) {
            ListNode poppedNode = stack.pop();
            int calcValue = poppedNode.getVal() + carry;
            carry = calcValue / 10;
            int newValue = calcValue % 10;
            ListNode newNode = new ListNode(newValue, null);
            if (resNode != null) newNode.next = resNode;
            resNode = newNode;
        }
        if (carry != 0) {
            ListNode newNode = new ListNode(carry, null);
            newNode.next = resNode;
            resNode = newNode;
        }
        return resNode;
    }
    public static void main(String[] args) {
        ListNode node = createList(new int[]{0, 9, 9});
        printList(plusOneList(node));
    }
}
