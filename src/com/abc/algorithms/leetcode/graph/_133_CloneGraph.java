package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _133_CloneGraph {
    private static class Node {
        private final int val;
        private List<Node> neighbors = new LinkedList<>();

        Node(int _val, List<Node> _neighbors) {
            this.val = _val;
            this.neighbors.addAll(_neighbors);
        }

        Node(int val) {
            this.val = val;
            this.neighbors = new LinkedList<>();
        }

        Node() {
            this.val = 0;
            this.neighbors = new LinkedList<>();
        }

        public int getVal() {
            return val;
        }
    }

    private static Node cloneGraph(Node node) {
        Set<Node> visited = new HashSet<>();
        Node newNode = new Node(node.val);

        bfs(node, newNode, visited);

        return newNode;
    }

    private static void bfs(Node node, Node newNode, Set<Node> visited) {
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> newQueue = new LinkedList<>();

        Map<Integer, Node> createdNodes = new HashMap<>();
        createdNodes.put(newNode.val, newNode);

        queue.add(node);

        newQueue.add(newNode);

        while (queue.size() > 0) {
            Node currentNode = queue.poll();
            Node newCurrentNode = newQueue.poll();

            for (Node n : currentNode.neighbors) {
                Node newNeighbor;
                if (!createdNodes.containsKey(n.val)) {
                    newNeighbor = new Node(n.val);
                    createdNodes.put(n.val, newNeighbor);
                } else
                    newNeighbor = createdNodes.get(n.val);

                if (!newCurrentNode.neighbors.contains(newNeighbor))
                    newCurrentNode.neighbors.add(newNeighbor);

                if (!visited.contains(n)) {
                    queue.add(n);
                    newQueue.add(newNeighbor);
                }
            }

            visited.add(currentNode);
        }
    }

    public static void main(String[] args) {
        Node oneNode = new Node(1);
        Node twoNode = new Node(2);
        Node threeNode = new Node(3);
        Node fourNode = new Node(4);

        oneNode.neighbors = Arrays.asList(twoNode, fourNode);
        twoNode.neighbors = Arrays.asList(oneNode, threeNode);
        threeNode.neighbors = Arrays.asList(twoNode, fourNode);
        fourNode.neighbors = Arrays.asList(oneNode, threeNode);

        Node newNode = cloneGraph(oneNode);
        System.out.println();
    }
}
