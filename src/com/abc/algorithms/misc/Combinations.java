package com.abc.algorithms.misc;

import java.util.*;

public record Combinations<T>(List<T> elems) {
    public Set<List<T>> getCombinations() {
        Set<List<T>> bases = new HashSet<>(Collections.singletonList(new ArrayList<>()));
        for (T elem : elems) bases.addAll(getCalculatedBases(elem, bases));
        return bases;
    }

    private Set<List<T>> getCalculatedBases(T elem, Set<List<T>> bases) {
        Set<List<T>> clonedBases = new HashSet<>();
        for (List<T> base : bases) {
            List<T> itr = new ArrayList<>(base);
            itr.add(elem);
            clonedBases.add(itr);
        }
        return clonedBases;
    }

    public static void main(String[] args) {
        Combinations<Integer> ic = new Combinations<>(List.of(1, 2, 3));
        System.out.println(ic.getCombinations());
        Combinations<Character> cc = new Combinations<>(List.of('h', 'h', 'i'));
        System.out.println(cc.getCombinations());
    }
}
