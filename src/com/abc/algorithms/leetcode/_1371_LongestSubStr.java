package com.abc.algorithms.leetcode;

import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Stream.concat;

public class _1371_LongestSubStr {
    private static void longestSubStr(String s) {
        int strLength = s.length();

        Map<Character, Integer> vowels = new HashMap<>() {
            {
                put('a', 0);
                put('e', 0);
                put('i', 0);
                put('o', 0);
                put('u', 0);
            }
        };

        int[][] result = new int[strLength + 1][strLength + 1];

        for (int iIdx = 1; iIdx <= strLength; iIdx++) {
            for (int jIdx = iIdx; jIdx <= strLength; jIdx++) {

            }
        }
    }

    private static int temp(String s) {
        int res = 0, cur = 0, n = s.length();
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1) >> 1;
            System.out.println("cur: " + cur + ", char: " + s.charAt(i));
            seen.putIfAbsent(cur, i);
            System.out.println("res, i - seen.get(cur) = " + res + ", " + i + " - (" + seen.get(cur) + ")");
            res = Math.max(res, i - seen.get(cur));
            System.out.println("res: " + res);
        }
        return res;
    }

    private static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedString = Arrays.toString(charArray);
            map.merge(sortedString, of(s), (a, b) -> Stream.of(a, b).flatMap(Collection::stream).collect(toUnmodifiableList()));

//            map.merge(sortedString, List.of(s), (a, b) -> concat(a.stream(), b.stream()).collect(toList()));

//            if (map.containsKey(sortedString)) {
//                List<String> lstrs = map.get(sortedString);
//                Collections.addAll(lstrs, s);
//                map.put(sortedString, lstrs);
//            } else
//                map.put(sortedString, new LinkedList<>(Collections.singletonList(s)));
        }

        return new ArrayList<>(map.values());
    }

    private static boolean backspaceCompare(String S, String T) {
        char[] sCharArray = S.toCharArray(), tCharArray = T.toCharArray();
        int sItrIdx = 0, tItrIdx = 0;

        for (int sIdx = 0; sIdx < sCharArray.length; sIdx++)
            if (sCharArray[sIdx] == '#')
                sItrIdx--;
            else if (sItrIdx + 1 > 0)
                sCharArray[sItrIdx++] = sCharArray[sIdx];
            else {
                sItrIdx = 0;
                sCharArray[sItrIdx++] = sCharArray[sIdx];
            }

        for (int tIdx = 0; tIdx < tCharArray.length; tIdx++)
            if (tCharArray[tIdx] == '#')
                tItrIdx--;
            else if (tItrIdx + 1 > 0)
                tCharArray[tItrIdx++] = tCharArray[tIdx];
            else {
                tItrIdx = 0;
                tCharArray[tItrIdx++] = tCharArray[tIdx];
            }

        if (sItrIdx < 0 && tItrIdx < 0)
            return true;

        if (sItrIdx != tItrIdx)
            return false;

        for (int itrIdx = 0; itrIdx < sItrIdx; itrIdx++)
            if (sCharArray[itrIdx] != tCharArray[itrIdx])
                return false;

        return true;
    }

    private void contArr(int[] nums) {
        int res = 0, leftPivot = 0, rightPivot = 0;

        Map<Character, Integer> elemMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        elemMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).map(sb::append);

//        for (int num : nums)
    }

    public static void main(String[] args) {
        System.out.println(temp("eleetminicoworoep"));
        int[] arr = {1, 1, 2, 2};
        Set<Integer> nums = IntStream.of(arr).collect(HashSet::new, HashSet::add, HashSet::addAll);

        int count = 0;
        for (int num : arr)
            if (nums.contains(1 + num))
                count++;

        System.out.println(count);

        System.out.println(backspaceCompare("a##c", "#a#c"));

        System.out.println(groupAnagrams(new String[]{"abc", "bca", "pqr", "apq", "qpr"}));
    }
}
