package com.abc.algorithms.chapter2;

public class LoopDetection {
    private static boolean loopDetection(LinkedList.Node<Integer> head) {
        LinkedList.Node<Integer> slowPointer = head, fastPointer = head;

        while (fastPointer.next != null && fastPointer.next.next != fastPointer) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.createLinkedList(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        linkedList.pntList();

        LinkedList<Integer> anotherLinkedList = new LinkedList<>();
        anotherLinkedList.createLinkedList(new Integer[]{100, 99, 98, 97});
        anotherLinkedList.pntList();

        // Point them to each other
        anotherLinkedList.seekTail().next = linkedList.head;
        linkedList.seekTail().next = anotherLinkedList.head;

        System.out.println(
                loopDetection(anotherLinkedList.head)
        );
    }
}
