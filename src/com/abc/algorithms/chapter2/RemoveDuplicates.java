package com.abc.algorithms.chapter2;

public class RemoveDuplicates {
    private static LinkedList.Node<Integer> removeDuplicates(LinkedList.Node<Integer> head) {
        LinkedList.Node<Integer> itrNode = head;

        LinkedList.Node<Integer> comparatorNode;
        LinkedList.Node<Integer> prevNode;

        while (itrNode.next != null) {
            comparatorNode = itrNode;
            while (comparatorNode.next != null) {
                prevNode = comparatorNode;
                comparatorNode = comparatorNode.next;

                if (itrNode.data.equals(comparatorNode.data)) {
                    prevNode.next = prevNode.next.next;
                }
            }
            itrNode = itrNode.next;
        }

        return head;
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.createLinkedList(new Integer[]{1, 3, 5, 7, 3, 4, 2, 1});

        linkedList.pntList();

        removeDuplicates(linkedList.head);

        linkedList.pntList();
    }
}
