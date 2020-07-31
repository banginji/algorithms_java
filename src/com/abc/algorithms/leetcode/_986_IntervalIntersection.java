package com.abc.algorithms.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class _986_IntervalIntersection {
    public static void main(String[] args) {
        int[] a = new int[]{0, 5};
        int[] b = new int[]{3, 7};
        Set<Integer> aa = IntStream.rangeClosed(a[0], a[1]).boxed().mapToInt(Integer::intValue).collect(HashSet::new, HashSet::add, HashSet::addAll);
        Set<Integer> bb = IntStream.rangeClosed(b[0], b[1]).boxed().mapToInt(Integer::intValue).collect(HashSet::new, HashSet::add, HashSet::addAll);
        aa.retainAll(bb);

        Map<String, Integer> map = new HashMap<>();
        map.computeIfPresent("", (s, i1) -> i1++);

        for (Map.Entry<String, Integer> entry: map.entrySet()) {

        }
    }
}
