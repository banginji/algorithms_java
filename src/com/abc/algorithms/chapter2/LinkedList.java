package com.abc.algorithms.chapter2;

public class LinkedList<T> {
    Node<T> head;

    LinkedList() {
        this.head = null;
    }

    void add(T data) {
        if (this.head == null) {
            this.head = new Node<>(data);
            return;
        }

        Node<T> tail = seekTail();
        tail.next = new Node<>(data);
    }

    Node<T> seekTail() {
        if (this.head == null) {
            return null;
        }

        Node<T> currentNode = this.head;
        while (currentNode.next != null)
            currentNode = currentNode.next;
        return currentNode;
    }

    Node<T> createLinkedList(T[] data) {
        Node<T> tail = seekTail();

        for (T elem : data) {
            if (tail == null) {
                tail = new Node<>(elem);
                this.head = tail;
                continue;
            }

            tail.next = new Node<>(elem);
            tail = tail.next;
        }

        return this.head;
    }

    Integer lengthOfList() {
        Node<T> currentNode = this.head;

        int length = 0;

        while (currentNode.next != null && currentNode.next.next != null) {
            length += 2;
            currentNode = currentNode.next.next;
        }

        return currentNode.next == null ? ++length : length + 2;
    }

    void pntList() {
        Node<T> currentNode = this.head;
        while (currentNode.next != null) {
            System.out.print(currentNode.data + " -> ");
            currentNode = currentNode.next;
        }
        System.out.print(currentNode.data);

        System.out.println();
    }

    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
