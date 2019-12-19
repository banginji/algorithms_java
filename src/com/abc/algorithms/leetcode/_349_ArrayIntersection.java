package com.abc.algorithms.leetcode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class _349_ArrayIntersection {
    private static List<Integer> arrayIntersection(int[] nums1, int[] nums2) {
        ConcurrentHashMap<Integer, Boolean> numsMap = new ConcurrentHashMap<>();

        int[] longerArray = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shorterArray = nums1.length < nums2.length ? nums1 : nums2;

        for (int num : longerArray) {
            numsMap.put(num, Boolean.FALSE);
        }

        for (int num : shorterArray) {
            if (numsMap.containsKey(num))
                numsMap.put(num, Boolean.TRUE);
        }

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Boolean> entry : numsMap.entrySet()) {
            if (entry.getValue())
                result.add(entry.getKey());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(arrayIntersection(new int[]{1, 23, 6}, new int[]{5, 66, 1}));
    }
}
