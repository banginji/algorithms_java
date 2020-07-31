package com.abc.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _990_EquationsEquality {
    private static boolean equationsPossible(String[] equations) {
        Map<Character, Character> vars = new HashMap<>();

        List<String> notEqualEqns = new ArrayList<>();

        for (String equation : equations) {
            char[] chars = equation.toCharArray();

            vars.putIfAbsent(chars[0], chars[0]);
            vars.putIfAbsent(chars[3], chars[3]);

            if (chars[1] == '!') {
                notEqualEqns.add(equation);
                continue;
            }

            union(chars[0], chars[3], vars);
        }

        for (String eqn: notEqualEqns) {
            char[] chars = eqn.toCharArray();

            if (find(chars[0], vars) == find(chars[3], vars))
                return false;
        }

        return true;
    }

    private static void union(Character varOne, Character varTwo, Map<Character, Character> vars) {
        Character actualVarOne = find(varOne, vars);
        Character actualVarTwo = find(varTwo, vars);

        if (actualVarOne.compareTo(actualVarTwo) != 0) {
            vars.put(actualVarOne, actualVarTwo);
        }
    }

    private static Character find(Character var, Map<Character, Character> vars) {
        return vars.get(var).compareTo(var) == 0 ? var : find(vars.get(var), vars);
    }

    public static void main(String[] args) {
        System.out.println(equationsPossible(new String[]{"a==b", "b!=d", "c==a"}));
    }
}
