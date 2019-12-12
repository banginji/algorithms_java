package com.abc.algorithms.misc;

import java.util.stream.IntStream;

public class RotateList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        IntStream.range(0, 10).boxed().forEach(list::add);

        list.pntList();

        list.rotateList(5);

        list.pntList();
    }

    private static class LinkedList<T> {
        Node<T> head;

        LinkedList() {
            this.head = null;
        }

        void add(T data) {
            Node<T> currentNode = seekTail();

            if (currentNode == null) {
                this.head = new Node<>(data);
                return;
            }

            currentNode.next = new Node<>(data);
        }

        void rotateList(int places) {

            while (places-- > 0) {
                Node<T> penultimateNode = seekPenultimateNode();
                Node<T> tail = penultimateNode.next;

                tail.next = this.head;
                this.head = tail;

                penultimateNode.next = null;
            }
        }

        Node<T> seekTail() {
            if (this.head == null) {
                return null;
            }

            Node<T> currentNode = this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            return currentNode;
        }

        Node<T> seekPenultimateNode() {
            Node<T> currentNode = this.head;

            while (currentNode.next.next != null) {
                currentNode = currentNode.next;
            }
            return currentNode;
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

        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }
    }
}
