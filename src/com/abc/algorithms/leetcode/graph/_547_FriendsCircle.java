package com.abc.algorithms.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

public class _547_FriendsCircle {
    private static int findCircleNum(int[][] m) {
        boolean[] isAFriend = new boolean[m.length];

        int friendsCircle = 0;

        for (int student = 0; student < m.length; student++) {
            if (!isAFriend[student]) {
                dfs(m, isAFriend, student);
                friendsCircle++;
            }
        }

        return friendsCircle;
    }

    private static void bfs(int[][] m, boolean[] isAFriend, int student) {
        Queue<Integer> studentQueue = new LinkedList<>();
        studentQueue.offer(student);
        isAFriend[student] = true;

        while (studentQueue.size() > 0) {
            int currentStudent = studentQueue.poll();

            for (int friend = 0; friend < m[currentStudent].length; friend++) {
                if (!isAFriend[friend] && m[currentStudent][friend] == 1) {
                    studentQueue.offer(friend);
                    isAFriend[friend] = true;
                }
            }
        }
    }

    private static void dfs(int[][] m, boolean[] isAFriend, int student) {
        isAFriend[student] = true;
        for (int friend = 0; friend < m[student].length; friend++) {
            if (m[student][friend] == 1 && !isAFriend[friend]) {
                isAFriend[friend] = true;
                dfs(m, isAFriend, friend);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                findCircleNum(new int[][]{
                        new int[]{1, 1, 0},
                        new int[]{1, 1, 0},
                        new int[]{0, 0, 1}
                })
        );
    }
}
