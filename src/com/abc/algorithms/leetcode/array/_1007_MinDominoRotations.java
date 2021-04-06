package com.abc.algorithms.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _1007_MinDominoRotations {
    private static int minDominoRotations(int[] A, int[] B) {
        Map<Integer, Set<Integer>> aMap = new HashMap<>();
        for (int idx = 0; idx < A.length; idx++) aMap.computeIfAbsent(A[idx], x -> new HashSet<>()).add(idx);
        Map<Integer, Set<Integer>> bMap = new HashMap<>();
        for (int idx = 0; idx < B.length; idx++) bMap.computeIfAbsent(B[idx], x -> new HashSet<>()).add(idx);
        int count = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Set<Integer>> entry : aMap.entrySet()) {
            Set<Integer> setOne = entry.getValue();
            Set<Integer> setTwo = bMap.getOrDefault(entry.getKey(), Collections.emptySet());
            if (isPossibleCandidate(A.length, setOne, setTwo)) count = Math.min(count, count(setOne, setTwo));
        }
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private static boolean isPossibleCandidate(int size, Set<Integer> setOne, Set<Integer> setTwo) {
        return Stream.of(setOne, setTwo).flatMap(Collection::stream).collect(Collectors.toSet()).size() == size;
    }

    private static int count(Set<Integer> setOne, Set<Integer> setTwo) {
        if (setOne.size() > setTwo.size())
            return ct(setOne, setTwo);
        else
            return ct(setTwo, setOne);
    }

    private static int ct(Set<Integer> larger, Set<Integer> smaller) {
        int count = smaller.size();
        for (Integer num : smaller) if (larger.contains(num)) count--;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                minDominoRotations(
                        new int[]{2, 1, 2, 4, 2, 2},
                        new int[]{5, 2, 6, 2, 3, 2}
                ) == 2
        );

        System.out.println(
                minDominoRotations(
                        new int[]{3, 5, 1, 2, 3},
                        new int[]{3, 6, 3, 3, 4}
                ) == -1
        );

        System.out.println(
                minDominoRotations(
                        new int[]{5, 2, 2, 2, 2, 2},
                        new int[]{2, 5, 5, 5, 5, 5}
                ) == 1
        );
    }
}
