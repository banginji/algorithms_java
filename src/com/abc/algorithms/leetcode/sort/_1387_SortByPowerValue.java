package com.abc.algorithms.leetcode.sort;

import java.util.*;

public class _1387_SortByPowerValue {
    private static Integer getKth(int lo, int hi, int k) {
        List<int[]> powers = new ArrayList<>();

        for (int num = lo; num <= hi; num++)
            powers.add(new int[]{num, calculatePower(num)});

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int[] power: powers)
            pq.offer(power);

        while (k-- > 1)
            pq.poll();

        return pq.poll()[0];
    }

    private static int calculatePower(int num) {
        return calculatePower(num, 0);
    }

    private static int calculatePower(int num, int powerValue) {
        if (num == 1)
            return powerValue;

        if (num % 2 == 0)
            return calculatePower(num / 2, powerValue + 1);
        else
            return calculatePower(3 * num + 1, powerValue + 1);
    }

    public static void main(String[] args) {
        System.out.println(
                getKth(12, 15, 2) == 13
        );

        System.out.println(
                getKth(1, 1, 1) == 1
        );

        System.out.println(
                getKth(7, 11, 4) == 7
        );

        System.out.println(
                getKth(10, 20, 5) == 13
        );

        System.out.println(
                getKth(1, 1000, 777) == 570
        );
    }
}
