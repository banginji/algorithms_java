package com.abc.algorithms.leetcode.graph;

import java.util.*;

public class _721_AccountsMerge {
    private static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailMap = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();

        for (List<String> account : accounts)
            for (int idx = 1; idx < account.size(); idx++) {
                nameMap.put(account.get(idx), account.get(0));
                emailMap.put(account.get(idx), account.get(idx));
            }

        for (List<String> account : accounts)
            for (int idx = 1; idx < account.size(); idx++)
                union(account.get(1), account.get(idx), emailMap);

        Map<String, Set<String>> res = new HashMap<>();

        for (List<String> account : accounts) {
            String pivotEmail = find(account.get(1), emailMap);
            for (int idx = 1; idx < account.size(); idx++)
                res.computeIfAbsent(pivotEmail, x -> new HashSet<>()).add(account.get(idx));
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : res.entrySet()) {
            List<String> emails = new ArrayList<>(entry.getValue());
            emails.add(0, nameMap.get(emails.get(0)));
            result.add(emails);
        }
        return result;
    }

    private static void union(String emailOne, String emailTwo, Map<String, String> emailMap) {
        emailMap.put(find(emailOne, emailMap), find(emailTwo, emailMap));
    }

    private static String find(String email, Map<String, String> emailMap) {
        return emailMap.get(email).equals(email) ? email : find(emailMap.get(email), emailMap);
    }

    public static void main(String[] args) {
        System.out.println(
                accountsMerge(
                        List.of(
                                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                                List.of("John", "johnnybravo@mail.com"),
                                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                                List.of("Mary", "mary@mail.com")
                        )
                )
        );

        System.out.println(
                accountsMerge(
                        List.of(
                                List.of("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"),
                                List.of("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"),
                                List.of("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"),
                                List.of("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"),
                                List.of("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co")
                        )
                )
        );
    }
}
