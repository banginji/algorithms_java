package com.abc.algorithms.leetcode;

public class TimeIt {
    public static void timeTaken(Runnable block) {
        long startTime = System.nanoTime();
        try {
            block.run();
        } finally {
            long stopTime = System.nanoTime();
            System.out.println("Time taken: " + (stopTime - startTime) / 1.0e9);
        }
    }
}
