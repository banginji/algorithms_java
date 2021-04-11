package com.abc.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class _1222_Queens {
    private static List<List<Integer>> queens(int[][] queens, int[] king) {
        boolean[][] board = new boolean[8][8];
        for (int[] queen : queens) board[queen[0]][queen[1]] = true;
        List<List<Integer>> res = new ArrayList<>();
        // up
        int ur = king[0];
        while (--ur >= 0) if (board[ur][king[1]]) {
            res.add(List.of(ur, king[1]));
            break;
        }
        // upper left
        int ulr = king[0], ulc = king[1];
        while (--ulr >= 0 && --ulc >= 0) if (board[ulr][ulc]) {
            res.add(List.of(ulr, ulc));
            break;
        }
        // left
        int lc = king[1];
        while (--lc >= 0) if (board[king[0]][lc]) {
            res.add(List.of(king[0], lc));
            break;
        }
        // left down
        int ldr = king[0], ldc = king[1];
        while (++ldr < 8 && --ldc >= 0) if (board[ldr][ldc]) {
            res.add(List.of(ldr, ldc));
            break;
        }
        // down
        int dr = king[0];
        while (++ur < 8) if (board[ur][king[1]]) {
            res.add(List.of(ur, king[1]));
            break;
        }
        // down right
        int drr = king[0], drc = king[1];
        while (++drr < 8 && ++drc < 8) if (board[drr][drc]) {
            res.add(List.of(drr, drc));
            break;
        }
        // right
        int rc = king[1];
        while (++rc < 8) if (board[king[0]][rc]) {
            res.add(List.of(king[0], rc));
            break;
        }
        // right up
        int rur = king[0], ruc = king[1];
        while (--rur >= 0 && ++ruc >= 0) if (board[rur][ruc]) {
            res.add(List.of(rur, ruc));
            break;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                queens(
                        new int[][]{
                                new int[]{0,1},
                                new int[]{1,0},
                                new int[]{4,0},
                                new int[]{0,4},
                                new int[]{3,3},
                                new int[]{2,4}
                        },
                        new int[]{0, 0}
                )
        );
    }
}
