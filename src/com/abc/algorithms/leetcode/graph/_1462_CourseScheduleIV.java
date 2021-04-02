package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1462_CourseScheduleIV {
    private static List<Boolean> courseSchedule(int n, int[][] preReqs, int[][] queries) {
        Map<Integer, List<Integer>> preReqMap = new HashMap<>();
        for (int[] preReq : preReqs) preReqMap.computeIfAbsent(preReq[0], x -> new ArrayList<>()).add(preReq[1]);
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) result.add(dfs(query[0], query[1], preReqMap));
        return result;
    }

    private static boolean dfs(int startCourse, int endCourse, Map<Integer, List<Integer>> preReqMap) {
        if (startCourse == endCourse) return true;
        for (Integer course : preReqMap.getOrDefault(startCourse, Collections.emptyList()))
            if (dfs(course, endCourse, preReqMap)) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(
                courseSchedule(
                        2,
                        new int[][]{
                                new int[]{1, 0}
                        },
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{1, 0}
                        }
                ).equals(List.of(false, true))
        );

        // No prereq (independent courses) but query is made on courses
        System.out.println(
                courseSchedule(
                        2,
                        new int[][]{},
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{1, 0}
                        }
                ).equals(List.of(false, false))
        );

        System.out.println(
                courseSchedule(
                        3,
                        new int[][]{
                                new int[]{1, 2},
                                new int[]{1, 0},
                                new int[]{2, 0}
                        },
                        new int[][]{
                                new int[]{1, 0},
                                new int[]{1, 2}
                        }
                ).equals(List.of(true, true))
        );

        System.out.println(
                courseSchedule(
                        3,
                        new int[][]{
                                new int[]{1, 0},
                                new int[]{2, 0}
                        },
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{2, 0}
                        }
                ).equals(List.of(false, true))
        );

        System.out.println(
                courseSchedule(
                        3,
                        new int[][]{
                                new int[]{0, 1},
                                new int[]{1, 2},
                                new int[]{2, 3},
                                new int[]{3, 4}
                        },
                        new int[][]{
                                new int[]{0, 4},
                                new int[]{4, 0},
                                new int[]{1, 3},
                                new int[]{3, 0}
                        }
                ).equals(List.of(true, false, true, false))
        );

        /**
         * Independent course without prereq and query made to that is a special case
         * that needs to be handled
         */
        System.out.println(
                courseSchedule(
                        3,
                        new int[][]{
                                new int[]{1, 2},
                        },
                        new int[][]{
                                new int[]{1, 0}
                        }
                ).equals(List.of(false))
        );
    }
}
