package com.abc.algorithms.chapter2;

import java.util.Stack;

public class Palindrome {
    private static boolean isPalindrome(LinkedList<Character> linkedList) {
        Stack<Character> stack = new Stack<>();

        int lengthOfList = linkedList.lengthOfList();

        int midPoint = lengthOfList % 2 == 0 ? lengthOfList / 2 : lengthOfList / 2 + 1;

        LinkedList.Node<Character> itrNode = linkedList.head;

        for (int i = 0; i < midPoint; i++) {
            stack.push(itrNode.data);
            itrNode = itrNode.next;
        }

        while (stack.elements().hasMoreElements()) {
            if (stack.pop() != itrNode.data)
                return false;
            itrNode = itrNode.next;
        }

        return true;
    }

    public static void main(String[] args) {
        LinkedList<Character> linkedList = new LinkedList<>();
        linkedList.createLinkedList(new Character[]{'p', 'a', 'l', 'i', 'i', 'i', 'a', 'p'});

        linkedList.pntList();

        System.out.println(isPalindrome(linkedList));
    }
}
