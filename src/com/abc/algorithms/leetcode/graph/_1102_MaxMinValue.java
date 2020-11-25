package com.abc.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _1102_MaxMinValue {
    private static int maxMinValue(int[][] A) {
        int[][] D = new int[A.length][A[0].length];
        for (int[] dr : D) Arrays.fill(dr, Integer.MAX_VALUE);
        D[0][0] = 0;

        int rows = A.length, cols = A[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{A[0][0], 0, 0});

        boolean[][] visited = new boolean[A.length][A[0].length];

        while (pq.size() > 0) {
            int[] cn = pq.poll();
            int cnwt = cn[0], cnr = cn[1], cnc = cn[2];

            if (cnr == rows - 1 && cnc == cols - 1) return D[cnr][cnc];

            if (visited[cnr][cnc]) continue;

            visited[cnr][cnc] = true;

            for (int[] n : ns(cnr, rows, cnc, cols)) {
                int nr = n[0], nc = n[1];
                if (visited[nr][nc]) continue;
                if (D[nr][nc] > Math.min(A[nr][nc], cnwt)) D[nr][nc] = Math.min(A[nr][nc], cnwt);
                pq.offer(new int[]{D[nr][nc], n[0], n[1]});
            }
        }
        return -1;
    }

    private static List<int[]> ns(int rowIdx, int rows, int colIdx, int cols) {
        List<int[]> nl = new ArrayList<>();

        // left n
        if (rowIdx - 1 >= 0) nl.add(new int[]{rowIdx - 1, colIdx});
        // right n
        if (rowIdx + 1 < rows) nl.add(new int[]{rowIdx + 1, colIdx});
        // upper n
        if (colIdx - 1 >= 0) nl.add(new int[]{rowIdx, colIdx - 1});
        // down n
        if (colIdx + 1 < cols) nl.add(new int[]{rowIdx, colIdx + 1});

        return nl;
    }

    public static void main(String[] args) {
        System.out.println(
                maxMinValue(
                        new int[][]{
                                new int[]{5, 4, 5},
                                new int[]{1, 2, 6},
                                new int[]{7, 4, 6}
                        }
                ) == 4
        );

        System.out.println(
                maxMinValue(
                        new int[][]{
                                new int[]{2, 2, 1, 2, 2, 2},
                                new int[]{1, 2, 2, 2, 1, 2}
                        }
                ) == 2
        );

        System.out.println(
                maxMinValue(
                        new int[][]{
                                new int[]{3, 4, 6, 3, 4},
                                new int[]{0, 2, 1, 1, 7},
                                new int[]{8, 8, 3, 2, 7},
                                new int[]{3, 2, 4, 9, 8},
                                new int[]{4, 1, 2, 0, 0},
                                new int[]{4, 6, 5, 4, 3}
                        }
                ) == 3
        );
    }
}
