package com.abc.algorithms.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Tsmf {
    private static int sumEq(int[] A, int[] B) {
        int leftASum = 0, leftBSum = 0, rightASum = IntStream.of(A).sum(), rightBSum = IntStream.of(B).sum();

        int count = 0;

        for (int idx = 0; idx < A.length; idx++) {
            leftASum += A[idx];
            leftBSum += B[idx];
            rightASum -= A[idx];
            rightBSum -= B[idx];

            if (leftASum == rightASum && leftBSum == rightBSum)
                count++;
        }
        return count;
    }

    private static int ab(String S) {
        if (!S.contains("A"))
            return 0;

        char[] chars = S.toCharArray();

        int bCount = 0;

        for (char aChar : chars)
            if (aChar == 'B') bCount++;

        int count = 0;

        int pivotIdx = 0;
        while (count < bCount / 2) {
            if (chars[pivotIdx] == 'B')
                count++;
            pivotIdx++;
        }

        while (chars[pivotIdx] == 'A')
            pivotIdx++;

        for (int idx = pivotIdx; idx < chars.length; idx++)
            if (chars[idx] == 'A')
                count++;

        return count;
    }

    private static int countNums(int[] A) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int num : A) {
            numMap.putIfAbsent(num, 0);
            numMap.computeIfPresent(num, (key, value) -> value + 1);
        }

        int count = 0;

        for (Map.Entry<Integer, Integer> entry : numMap.entrySet())
            if (entry.getKey().equals(entry.getValue()))
                count++;

        return count;
    }

    public static void main(String[] args) {
//        System.out.println(sumEq(new int[]{}, new int[]{}));
        System.out.println(ab("BABAABAAAB"));
//        System.out.println(countNums(new int[]{5, 5, 5, 5, 5}));
    }
}
