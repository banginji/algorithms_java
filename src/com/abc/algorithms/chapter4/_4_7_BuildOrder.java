package com.abc.algorithms.chapter4;

import com.abc.algorithms.datastructure.Graph;
import com.abc.algorithms.misc.Tuple;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class _4_7_BuildOrder {
    // TODO - does not work for some cases
    private static void buildOrder(List<Character> projects, List<Tuple<Character, Character>> deps) {
        Map<Character, Graph.Node<Character>> nodes = createNodes(projects);

        if (hasCycle(nodes, deps)) {
            System.out.println("Graph has a cycle");
            return;
        }

        buildGraph(nodes, deps);

        while (nodes.values().stream()
                .map(Graph.Node::isNotTraversed)
                .reduce(false, (n1, n2) -> n1 || n2)
        )
            nodes.entrySet().stream()
                    .filter(entrySet -> entrySet.getValue().isNotTraversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, val -> val.getValue().getNeighbors().size()))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .ifPresent(entrySet -> graphTraversal(nodes.get(entrySet.getKey())));
    }

    private static Map<Character, Graph.Node<Character>> createNodes(List<Character> projects) {
        return projects.stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                Graph.Node::new
                        )
                );
    }

    private static void buildGraph(Map<Character, Graph.Node<Character>> projects, List<Tuple<Character, Character>> deps) {
        deps.forEach(dep ->
                projects.get(dep.getFirst())
                        .addNeighbor(projects.get(dep.getSecond()))
        );

        projects.forEach((proj, dependencies) -> System.out.println(proj + ": " + dependencies));
    }

    private static boolean hasCycle(Map<Character, Graph.Node<Character>> nodes, List<Tuple<Character, Character>> deps) {
        Map<Graph.Node<Character>, List<Graph.Node<Character>>> nodeDepMap = depMap(nodes, deps);

        for (Graph.Node<Character> node : nodes.values()) {
            if (hasCycle(node, nodeDepMap))
                return true;
        }

        return false;
    }

    private static Map<Graph.Node<Character>, List<Graph.Node<Character>>> depMap(
            Map<Character, Graph.Node<Character>> projects,
            List<Tuple<Character, Character>> deps
    ) {
        return deps.stream()
                .collect(
                        Collectors.groupingBy(
                                dep -> projects.get(dep.getFirst()),
                                Collectors.mapping(
                                        dep -> projects.get(dep.getSecond()),
                                        Collectors.toList()
                                )
                        )
                );
    }

    private static boolean hasCycle(
            Graph.Node<Character> currentNode,
            Map<Graph.Node<Character>, List<Graph.Node<Character>>> depMap
    ) {
        if (currentNode.isProcessing())
            return true;
        if (depMap.containsKey(currentNode)) {
            currentNode.setProcessing(true);
            for (Graph.Node<Character> neighbor : depMap.get(currentNode))
                if (hasCycle(neighbor, depMap))
                    return true;
            currentNode.setProcessing(false);
        } else
            return false;
        return false;
    }

    private static void graphTraversal(Graph.Node<Character> startNode) {
        startNode.setNotTraversed(false);
        LinkedList<Graph.Node<Character>> queue = new LinkedList<>();
        queue.push(startNode);

        while (queue.size() > 0) {
            Graph.Node<Character> currentNode = queue.pop();
            System.out.print(currentNode.getData() + " -> ");

            currentNode.getNeighbors().stream()
                    .filter(Graph.Node::isNotTraversed)
                    .sorted(Comparator.comparing(node -> node.getNeighbors().size()))
                    .peek(node -> node.setNotTraversed(false))
                    .forEach(queue::push);
        }
    }

    public static void main(String[] args) {
        System.out.println("Build order implementation");

        buildOrder(
                Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'),
                Arrays.asList(
                        new Tuple<>('a', 'b'),
                        new Tuple<>('b', 'c'),
                        new Tuple<>('a', 'c'),
                        new Tuple<>('d', 'e'),
                        new Tuple<>('b', 'd'),
                        new Tuple<>('e', 'f'),
                        new Tuple<>('a', 'f'),
                        new Tuple<>('h', 'i'),
                        new Tuple<>('h', 'j'),
                        new Tuple<>('i', 'j'),
                        new Tuple<>('g', 'j')
                )
        );

        /*
         *
         *  e - d - b      g - j
         *   \     / \       /  \
         *    f - a - c     h -  i
         *
         */
    }
}
