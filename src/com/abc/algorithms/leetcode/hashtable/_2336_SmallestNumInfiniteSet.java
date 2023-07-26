package com.abc.algorithms.leetcode.hashtable;

import java.util.SortedSet;
import java.util.TreeSet;

public class _2336_SmallestNumInfiniteSet {
    private static class SmallestInfiniteSet {
        SortedSet<Integer> set;
        SmallestInfiniteSet() {
            set = new TreeSet<>();
        }

        private int popSmallest() {
            int first = set.first();
            set.remove(set.first());
            if (set.isEmpty()) set.add(first + 1);
            System.out.printf("Popped %d\n", first);
            return first;
        }

        private void addBack(int num) {
            while (num > 0) set.add(num--);
        }

        private void printSet() {
            System.out.println("Printing elements");
            for(int elem: set) System.out.println(elem);
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet set = new SmallestInfiniteSet();
        set.addBack(2);
        set.printSet();
        set.popSmallest();
        set.popSmallest();
        set.popSmallest();
        set.printSet();
    }
}
