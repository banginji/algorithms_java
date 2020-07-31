package com.abc.algorithms.datastructure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    public static class Node<T> {
        private final T data;
        private final List<Node<T>> neighbors;
        private boolean notTraversed = true;
        private boolean processing = false;

        public Node(T data) {
            this.data = data;
            this.neighbors = new LinkedList<>();
        }

        public Node(T data, List<Node<T>> neighbors) {
            this.data = data;
            this.neighbors = neighbors;
        }

        public void addNeighbor(Node<T> neighbor) {
            Collections.addAll(this.neighbors, neighbor);
        }

        public T getData() {
            return data;
        }

        public List<Node<T>> getNeighbors() {
            return neighbors;
        }

        public boolean isNotTraversed() {
            return notTraversed;
        }

        public void setNotTraversed(boolean notTraversed) {
            this.notTraversed = notTraversed;
        }

        public boolean isProcessing() {
            return processing;
        }

        public void setProcessing(boolean processing) {
            this.processing = processing;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", neighbors=" + neighbors +
                    ", notTraversed=" + notTraversed +
                    '}';
        }
    }
}
