package com.abc.algorithms.leetcode.graph;

public class _765_CouplesHoldingHands {
    private static int minSwapsCouples(int[] row) {
        int[] relationShips = new int[row.length];

        for (int person = 0; person < row.length - 1; person += 2) {
            relationShips[row[person]] = row[person + 1];
            relationShips[row[person + 1]] = row[person];
        }

        int count = 0;
        for (int person = 0; person < relationShips.length - 1; person += 2)
            count = fix(person, relationShips, count);

        return count;
    }

    /**
     * 0, 4, 2, 5, 1, 3
     * 0, 1, 2, 5, 4, 3
     */
    private static int fix(int person, int[] relationShips, int count) {
        if (relationShips[person] == person + 1)
            return count;

        if (relationShips[person] != person + 1) {
            int firstOriginalPerson = relationShips[person];
            int secondOriginalPerson = relationShips[person + 1];

            relationShips[person] = person + 1;
            relationShips[person + 1] = person;

            relationShips[firstOriginalPerson] = secondOriginalPerson;
            relationShips[secondOriginalPerson] = firstOriginalPerson;

            int newPerson = firstOriginalPerson % 2 == 0 ? firstOriginalPerson : firstOriginalPerson - 1;

            return fix(newPerson, relationShips, ++count);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(minSwapsCouples(new int[]{5, 4, 2, 6, 3, 1, 0, 7}));
    }
}
