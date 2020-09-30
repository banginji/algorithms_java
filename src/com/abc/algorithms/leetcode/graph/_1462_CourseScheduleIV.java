package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _1462_CourseScheduleIV {
    private static List<Boolean> courseSchedule(int n, int[][] preReqs, int[][] queries) {
        Map<Integer, Integer> inDegree = new HashMap<>();

        /**
         *
         * inDegree does NOT contain all courses on purpose for this problem
         *
         *         for (int course = 0; course < n; course++)
         *             inDegree.put(course, 0);
         *
         * If it is present then it will either appear in front of dependent
         * courses or after which will lead to inconclusive results
         *
         */
        for (int[] preReq : preReqs) {
            inDegree.putIfAbsent(preReq[0], 0);
            inDegree.putIfAbsent(preReq[1], 0);
            inDegree.computeIfPresent(preReq[1], (k, v) -> v + 1);
        }

        Map<Integer, List<Integer>> depMap = new HashMap<>();

        for (int[] preReq : preReqs)
            depMap.computeIfAbsent(preReq[0], x -> new ArrayList<>()).add(preReq[1]);

        Queue<Integer> queue = new LinkedList<>();

        inDegree.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .map(Map.Entry::getKey)
                .forEach(queue::offer);

        LinkedHashSet<Integer> courseList = new LinkedHashSet<>();

        while (queue.size() > 0) {
            int currentCourse = queue.poll();

            courseList.add(currentCourse);

            for (Integer course : depMap.getOrDefault(currentCourse, Collections.emptyList())) {
                inDegree.computeIfPresent(course, (k, v) -> v - 1);
                if (inDegree.getOrDefault(course, -1) == 0)
                    queue.offer(course);
            }
        }

        List<Boolean> result = new ArrayList<>();

        for (int[] query : queries) {
            int preReqCourse = query[0];
            int depCourse = query[1];

            /**
             * If either of the course is NOT found in course list then add 'false'
             * for that query
             */
            if (!courseList.contains(preReqCourse) || !courseList.contains(depCourse)) {
                result.add(false);
                continue;
            }

            for (Integer course : courseList)
                if (course == preReqCourse || course == depCourse)
                    if (course == depCourse) {
                        result.add(false);
                        break;
                    } else {
                        result.add(true);
                        break;
                    }
        }

        return result;
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
