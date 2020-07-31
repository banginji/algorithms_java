package com.abc.algorithms.leetcode.graph;

import java.util.Stack;

public class _207_CourseSchedule {
    // TODO: How does it handle cycles?
    private static boolean canFinish(int numCourses, int[][] preRequisites) {
        int[] inDegree = new int[numCourses];

        for (int[] preReq : preRequisites)
            inDegree[preReq[0]]++;

        Stack<Integer> stack = new Stack<>();

        for (int course = 0; course < inDegree.length; course++)
            if (inDegree[course] == 0) stack.push(course);

        if (stack.isEmpty()) return false;

        int count = 0;

        while (stack.size() > 0) {
            int currentCourse = stack.pop();

            count++;

            for (int[] preReq : preRequisites)
                if (preReq[1] == currentCourse)
                    if (--inDegree[preReq[0]] == 0)
                        stack.push(preReq[0]);
        }

        return count == numCourses;
    }

    public static void main(String[] args) {
        System.out.println(
                canFinish(
                        2,
                        new int[][]{
                                new int[]{0, 1}
                        }
                )
        );
    }
}
