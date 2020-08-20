package com.abc.algorithms.chapter4;

import java.util.*;

public class _4_7_BuildOrder {
    private static List<Character> buildOrder(Character[] projects, Character[][] deps) {
        // In Degree
        Map<Character, Integer> inDegree = new HashMap<>();

        for (Character project: projects)
            inDegree.putIfAbsent(project, 0);

        for (Character[] dep: deps)
            inDegree.computeIfPresent(dep[1], (key, value) -> value + 1);

        // Deps Map
        Map<Character, List<Character>> depsMap = new HashMap<>();

        for (Character[] dep: deps)
            depsMap.computeIfAbsent(dep[0], x -> new ArrayList<>()).add(dep[1]);
        
        Stack<Character> stack = new Stack<>();

        inDegree.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .map(Map.Entry::getKey)
                .forEach(stack::push);

        List<Character> result = new ArrayList<>();

        while (stack.size() > 0) {
            Character currentProject = stack.pop();
            result.add(currentProject);

            for (Character project: depsMap.getOrDefault(currentProject, Collections.emptyList())) {
                inDegree.computeIfPresent(project, (key, value) -> value - 1);
                if (inDegree.getOrDefault(project, -1) == 0)
                    stack.push(project);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Build order implementation");

        Character[] projects = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        Character[][] dependencies = {
                {'a', 'b'},
                {'b', 'c'},
                {'a', 'c'},
                {'a', 'c'},
                {'d', 'e'},
                {'b', 'd'},
                {'e', 'f'},
                {'a', 'f'},
                {'h', 'i'},
                {'h', 'j'},
                {'i', 'j'},
                {'g', 'j'}};

        System.out.println(buildOrder(projects, dependencies));

        /*
         *
         *  e - d - b      g - j
         *   \     / \       /  \
         *    f - a - c     h -  i
         *
         */
    }
}
