package com.abc.algorithms.misc;

import java.util.*;

public class CodeWars {
    private static List<Integer> sumConsecutives(List<Integer> s) {
        int itrIdx = 0;
        List<Integer> res = new ArrayList<>();
        while (itrIdx < s.size()) {
            int itrNum = s.get(itrIdx++), itrSum = itrNum;
            while (itrIdx < s.size() && s.get(itrIdx) == itrNum) {
                itrSum += itrNum;
                itrIdx++;
            }
            res.add(itrSum);
        }
        return res;
    }

    public static String stripComments(String text, String[] commentSymbols) {
        Set<Character> set = new HashSet<>();
        for (String s : commentSymbols) set.add(s.charAt(0));
        StringBuilder sb = new StringBuilder();
        char[] ca = text.toCharArray();
        int itrIdx = 0;
        while (itrIdx < ca.length) {
            if (set.contains(ca[itrIdx])) {
                while (itrIdx<ca.length && ca[itrIdx] != '\n') itrIdx++;
                if (itrIdx >= ca.length) break;
            }
            sb.append(ca[itrIdx++]);
        }
        int resIdx = 0;
        while (resIdx < sb.length() - 1) {
            if (sb.charAt(resIdx + 1) == '\n' && sb.charAt(resIdx) == ' ') sb.delete(resIdx, resIdx + 1);
            resIdx++;
        }
        if (sb.charAt(resIdx) == ' ') sb.delete(resIdx, resIdx + 1);
        return sb.toString();
    }

    private static Map<String, List<String>> alter() {
        return Map.of(
                "1", List.of("2", "4"),
                "2", List.of("1", "3", "5"),
                "3", List.of("2", "6"),
                "4", List.of("1", "5", "7"),
                "5", List.of("2", "4", "6", "8"),
                "6", List.of("3", "5", "7"),
                "7", List.of("4", "8"),
                "8", List.of("5", "7", "9", "0"),
                "9", List.of("6", "8"),
                "0", List.of("8")
        );
    }

    public static void main(String[] args) {
//        System.out.println(
//                sumConsecutives(
//                        List.of(1, 4, 4, 4, 0, 4, 3, 3, 1)
//                )
//        );

        System.out.println(
                stripComments(
                        "apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}
                )
        );

        System.out.println(
                stripComments(
                        "a #b\nc\nd $e f g", new String[] { "#", "$" }
                )
        );

        System.out.println(new StringBuilder().append("").toString());
    }
}
