package com.abc.algorithms.leetcode.array;

public class _3638_MaxBalancedShip {
    public int maxBalancedShipments(int[] weight) {
        int ptr = 0;
        int itrMax = Integer.MIN_VALUE;
        int count = 0;
        while (ptr < weight.length) {
            if (weight[ptr] < itrMax) {
                count++; ptr++;
                itrMax = Integer.MIN_VALUE;
                continue;
            }
            itrMax = weight[ptr++];
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                new _3638_MaxBalancedShip()
                        .maxBalancedShipments(
                                new int[]{2, 5, 1, 4, 3}
                        ) == 2
        );

        System.out.println(
                new _3638_MaxBalancedShip()
                        .maxBalancedShipments(
                                new int[]{4, 4}
                        ) == 0
        );

        System.out.println(
                new _3638_MaxBalancedShip()
                        .maxBalancedShipments(
                                new int[]{45, 7, 234, 86, 23, 9, 3, 2, 5, 7, 9, 10, 22, 43, 21, 5, 785, 234, 745, 88}
                        ) == 7
        );
    }
}
