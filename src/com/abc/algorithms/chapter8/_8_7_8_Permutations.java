package com.abc.algorithms.chapter8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _8_7_8_Permutations {
    record Permutation<T>(List<T> elems) {
        public Set<List<T>> getPermutations() {
            Set<List<T>> permutations = new HashSet<>(Collections.singletonList(new ArrayList<>()));
            for (T elem : this.elems) permutations = getPermBases(permutations, elem);
            return permutations;
        }

        private Set<List<T>> getPermBases(Set<List<T>> base, T elem) {
            Set<List<T>> bases = new HashSet<>();

            for (List<T> baseElem : base)
                bases.addAll(
                        IntStream
                                .range(0, baseElem.size() + 1)
                                .boxed()
                                .map(idx -> {
                                    List<T> clonedBase = new ArrayList<>(baseElem);
                                    clonedBase.add(idx, elem);
                                    return clonedBase;
                                })
                                .collect(Collectors.toList())
                );

            return bases;
        }
    }

    public static void main(String[] args) {
        Permutation<Integer> permutation = new Permutation<>(List.of(1, 2, 3));
        System.out.println(permutation.getPermutations());
    }
}
