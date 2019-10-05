package com.abc.algorithms.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CacheImpl {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.range(0, 10).boxed().forEach(list::add);

        Map<Integer, Node<Integer>> cache = new HashMap<>();

        Node<Integer> node = list.getHead();

        while (node.next != null) {
            cache.put(node.data, node);
            node = node.next;
        }

        System.out.println("Before");
        list.printList();
        list.moveNodeToHead(cache.get(5));
        System.out.println("\nAfter");
        list.printList();
    }

    private static class LinkedList<T> {
        Node<T> head;

        LinkedList() {
            this.head = null;
        }

        void add(T data) {
            if (this.head == null) {
                this.head = new Node<>(data);
                return;
            }

            Node<T> tail = getTail();
            Node<T> newNode = new Node<>(data);
            tail.next = newNode;
            newNode.prev = tail;
        }

        void moveNodeToHead(Node<T> node) {
            Node<T> next = node.next;
            Node<T> prev = node.prev;

            next.prev = prev;
            prev.next = next;

            node.next = this.head;
            node.prev = null;
            this.head = node;
        }

        Node<T> getHead() {
            return this.head;
        }

        Node<T> getTail() {
            Node<T> curr = this.head;
            while (curr.next != null) {
                curr = curr.next;
            }
            return curr;
        }

        void printList() {
            Node<T> curr = this.head;
            while (curr.next != null) {
                System.out.print(curr.data + " -> ");
                curr = curr.next;
            }
            System.out.print(curr.data);
        }
    }

    static class Node<P> {
        P data;
        Node<P> next;
        Node<P> prev;

        Node(P data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}
