package com.abc.algorithms.leetcode.ll;

public class LinkedListHelper {
    public static class ListNode {
        private final int val;
        public ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return this.val;
        }

        public ListNode getNext() {
            return this.next;
        }
    }

    public static ListNode createList(int[] nums) {
        return createList(0, nums);
    }

    private static ListNode createList(int idx, int[] nums) {
        if (idx >= nums.length) return null;
        return new ListNode(nums[idx], createList(idx + 1, nums));
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.getVal());
            if (node.next != null)  System.out.print(" -> ");
            node = node.next;
        }
    }
}
