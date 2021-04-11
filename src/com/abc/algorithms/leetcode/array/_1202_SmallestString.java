package com.abc.algorithms.leetcode.array;

import java.util.*;

public class _1202_SmallestString {
    private static String smallestString(String s, List<List<Integer>> pairs) {
        char[] ca = s.toCharArray();
        int[] parent = new int[ca.length];
        for (int idx = 0; idx < parent.length; idx++) parent[idx] = idx;
        for (List<Integer> pair : pairs) union(pair.get(0), pair.get(1), parent);
        Map<Integer, Set<Integer>> idxMap = new HashMap<>();
        for (List<Integer> pair : pairs) {
            int rootIdx = find(pair.get(0), parent);
            idxMap.putIfAbsent(rootIdx, new HashSet<>());
            idxMap.computeIfPresent(rootIdx, (k, v) -> {
                v.add(pair.get(0));
                v.add(pair.get(1));
                return v;
            });
        }
        Set<Integer> rootKeys = idxMap.keySet();
        List<int[]> indices = new ArrayList<>();
        for (Integer rootKey : rootKeys) {
            int[] idxs = idxMap.get(rootKey).stream().mapToInt(Integer::intValue).toArray();
            indices.add(idxs);
        }
        for (int[] idxs : indices) {
            char[] chars = new char[idxs.length];
            int itrIdx = 0;
            for (int idx : idxs) chars[itrIdx++] = ca[idx];
            Arrays.sort(chars);
            itrIdx = 0;
            for (int idx : idxs) ca[idx] = chars[itrIdx++];
        }
        StringBuilder sb = new StringBuilder();
        for (char c : ca) sb.append(c);
        return sb.toString();
    }

    private static void union(int idxOne, int idxTwo, int[] parent) {
        parent[find(idxOne, parent)] = parent[find(idxTwo, parent)];
    }

    private static int find(int idx, int[] parent) {
        return parent[idx] == idx ? idx : find(parent[idx], parent);
    }

    public static void main(String[] args) {
        System.out.println(
                smallestString(
                        "dcab",
                        List.of(
                                List.of(0, 3),
                                List.of(1, 2)
                        )
                ).equals("bacd")
        );

        System.out.println(
                smallestString(
                        "dcab",
                        List.of(
                                List.of(0, 3),
                                List.of(1, 2),
                                List.of(0, 2)
                        )
                ).equals("abcd")
        );

        System.out.println(
                smallestString(
                        "cba",
                        List.of(
                                List.of(0, 1),
                                List.of(1, 2)
                        )
                ).equals("abc")
        );

        System.out.println(
                smallestString(
                        "kefnekunw",
                        List.of(
                                List.of(0, 4),
                                List.of(1, 5),
                                List.of(0, 2),
                                List.of(1, 4),
                                List.of(6, 7)
                        )
                ).equals("eefnkknuw")
        );
    }
}
