package com.abc.algorithms.leetcode.dp;

import com.abc.algorithms.leetcode.TimeIt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _1626_TeamNoConflicts {
    /**
     * Detailed understanding of the train of thought
     * https://leetcode.com/problems/best-team-with-no-conflicts/discuss/909417/I-understand-the-solution-but-HOW-do-I-think-to-GET-there-myself-Explanation-and-Solution-O(n2)
     */
    private static int bestTeamScore(int[] scores, int[] ages) {
        // Sort scores by age using heap sort
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        for (int idx = 0; idx < ages.length; idx++) pq.offer(new int[]{ages[idx], scores[idx]});

        int[] sc = new int[scores.length];

        int itrIdx = 0;
        while (pq.size() > 0) sc[itrIdx++] = pq.poll()[1];

        // Modified LIS
        int[] teamScores = new int[scores.length];

        for (int iIdx = 0; iIdx < sc.length; iIdx++) {
            teamScores[iIdx] = sc[iIdx];
            for (int jIdx = 0; jIdx < iIdx; jIdx++)
                if (sc[iIdx] >= sc[jIdx])
                    teamScores[iIdx] = Math.max(teamScores[iIdx], sc[iIdx] + teamScores[jIdx]);
        }

        return Arrays.stream(teamScores).max().orElse(0);
    }

    private static int bestTeamScoreTd(int[] scores, int[] ages) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int idx = 0; idx < ages.length; idx++)
            pq.offer(new int[]{ages[idx], scores[idx]});

        List<int[]> players = new ArrayList<>();

        while (pq.size() > 0) players.add(pq.poll());

        return bestTeamScoreTd(players, 0, Integer.MIN_VALUE);
    }

    private static int bestTeamScoreTd(List<int[]> players, int idx, int age) {
        if (idx >= players.size()) return 0;

        if (players.get(idx)[0] >= age)
            return Math.max(
                    players.get(idx)[1] + bestTeamScoreTd(players, idx + 1, players.get(idx)[0]),
                    bestTeamScoreTd(players, idx + 1, age)
            );
        else
            return bestTeamScoreTd(players, idx + 1, age);
    }

    public static void main(String[] args) {
        System.out.println(
                bestTeamScore(
                        new int[]{1, 3, 5, 10, 15},
                        new int[]{1, 2, 3, 4, 5}
                ) == 34
        );

        System.out.println(
                bestTeamScore(
                        new int[]{4, 5, 6, 5},
                        new int[]{2, 1, 2, 1}
                ) == 16
        );

        System.out.println(
                bestTeamScore(
                        new int[]{1, 2, 3, 5},
                        new int[]{8, 9, 10, 1}
                ) == 6
        );

        System.out.println(
                bestTeamScore(
                        new int[]{1, 3, 7, 3, 2, 4, 10, 7, 5},
                        new int[]{4, 5, 2, 1, 1, 2, 4, 1, 4}
                ) == 29
        );

        System.out.println(
                bestTeamScoreTd(
                        new int[]{1, 3, 5, 10, 15},
                        new int[]{1, 2, 3, 4, 5}
                ) == 34
        );

        System.out.println(
                bestTeamScoreTd(
                        new int[]{4, 5, 6, 5},
                        new int[]{2, 1, 2, 1}
                ) == 16
        );

        System.out.println(
                bestTeamScoreTd(
                        new int[]{1, 2, 3, 5},
                        new int[]{8, 9, 10, 1}
                ) == 6
        );

        System.out.println(
                bestTeamScoreTd(
                        new int[]{1, 3, 7, 3, 2, 4, 10, 7, 5},
                        new int[]{4, 5, 2, 1, 1, 2, 4, 1, 4}
                ) == 29
        );

        /**
         * Time taken: 0.001107501
         * Time taken: 5.0349E-4
         * Time taken: 4.05107E-4
         * Time taken: 3.89274E-4
         * Time taken: 3.9177E-4
         * Time taken: 4.18789E-4
         * Time taken: 2.97435E-4
         * Time taken: 2.56625E-4
         * Time taken: 2.54975E-4
         * Time taken: 2.54069E-4
         *
         * ~0.0002 sec
         */

        System.out.println("Itr A");

        int itrA = 0;
        while (itrA++ < 10)
            TimeIt.timeTaken(() -> bestTeamScore(
                    new int[]{37, 770, 876, 686, 235, 701, 54, 919, 406, 367, 653, 922, 745, 464, 445, 94, 293, 252, 819, 212, 213, 611, 333, 320, 300, 626, 751, 226, 770, 284, 593, 242, 589, 432, 863, 753, 834, 967, 326, 844, 314, 314, 995, 836, 464, 707, 75, 149, 1, 441, 601, 133, 915, 452, 723, 357, 94, 890, 416, 979, 909, 214, 118, 532, 884, 43, 75, 262, 115, 198, 67, 811, 239, 349, 589, 555, 896, 789, 455, 101, 701, 620},
                    new int[]{26, 81, 64, 86, 77, 32, 65, 57, 54, 72, 50, 74, 34, 73, 33, 37, 52, 43, 62, 46, 86, 23, 82, 62, 24, 4, 69, 42, 16, 60, 48, 7, 23, 83, 30, 4, 59, 25, 96, 27, 73, 90, 24, 87, 48, 46, 37, 93, 2, 44, 54, 6, 39, 4, 43, 95, 70, 67, 60, 34, 50, 38, 94, 89, 14, 87, 36, 30, 84, 37, 30, 61, 29, 4, 73, 57, 56, 51, 85, 55, 63, 55}
            ));

        /**
         * Time taken: 0.730031584
         * Time taken: 0.819497614
         * Time taken: 0.429957423
         * Time taken: 0.319419533
         * Time taken: 0.319276082
         * Time taken: 0.315627492
         * Time taken: 0.337811001
         * Time taken: 0.330257233
         * Time taken: 0.324730485
         * Time taken: 0.311887732
         *
         * ~0.3 sec
         */

        System.out.println("Itr B");

        int itrB = 0;
        while (itrB++ < 10)
            TimeIt.timeTaken(() -> bestTeamScoreTd(
                    new int[]{37, 770, 876, 686, 235, 701, 54, 919, 406, 367, 653, 922, 745, 464, 445, 94, 293, 252, 819, 212, 213, 611, 333, 320, 300, 626, 751, 226, 770, 284, 593, 242, 589, 432, 863, 753, 834, 967, 326, 844, 314, 314, 995, 836, 464, 707, 75, 149, 1, 441, 601, 133, 915, 452, 723, 357, 94, 890, 416, 979, 909, 214, 118, 532, 884, 43, 75, 262, 115, 198, 67, 811, 239, 349, 589, 555, 896, 789, 455, 101, 701, 620},
                    new int[]{26, 81, 64, 86, 77, 32, 65, 57, 54, 72, 50, 74, 34, 73, 33, 37, 52, 43, 62, 46, 86, 23, 82, 62, 24, 4, 69, 42, 16, 60, 48, 7, 23, 83, 30, 4, 59, 25, 96, 27, 73, 90, 24, 87, 48, 46, 37, 93, 2, 44, 54, 6, 39, 4, 43, 95, 70, 67, 60, 34, 50, 38, 94, 89, 14, 87, 36, 30, 84, 37, 30, 61, 29, 4, 73, 57, 56, 51, 85, 55, 63, 55}
            ));
    }
}
