package com.abc.algorithms.leetcode.uncategorized;

public class _657_RobotInGrid {
    private static boolean judge(String moves) {
        int x=0, y=0;

        for (char move: moves.toCharArray()) {
            if (move == 'U')
                x--;
            if (move == 'D')
                x++;
            if (move == 'L')
                y--;
            if (move == 'R')
                y++;
        }

        return x == 0 && y == 0;
    }

    public static void main(String[] args) {
        System.out.println(judge("DUU"));
    }
}
