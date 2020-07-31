package com.abc.algorithms.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupEmails {
    private static List<List<String>> groupEmails(String[][] emailGroups) {
        Map<String, String> emailMap = new HashMap<>();

        for (String[] emailGroup : emailGroups)
            for (String email: emailGroup) {
                emailMap.putIfAbsent(email, email);
                union(emailGroup[0], email, emailMap);
            }

        Map<String, List<String>> result = new HashMap<>();

        for (String email : emailMap.keySet()) {
            String parentEmail = find(email, emailMap);
            result.computeIfAbsent(parentEmail, x -> new ArrayList<>()).add(email);
        }

        return new ArrayList<>(result.values());
    }

    private static void union(String emailOne, String emailTwo, Map<String, String> emailMap) {
        String parentOne = find(emailOne, emailMap);
        String parentTwo = find(emailTwo, emailMap);

        if (!parentOne.equals(parentTwo))
            emailMap.put(parentOne, parentTwo);
    }

    private static String find(String email, Map<String, String> emailMap) {
        return emailMap.get(email).equals(email) ? email : find(emailMap.get(email), emailMap);
    }

    private static List<List<String>> emailsMerge(String[][] emailGroups) {
        DSU dsu = new DSU();

        Map<String, Integer> emailToID = new HashMap<>();

        int id = 0;

        for (String[] emailGroup : emailGroups)
            for (String email : emailGroup) {
                emailToID.putIfAbsent(email, id++);
                dsu.union(emailToID.get(emailGroup[0]), emailToID.get(email));
            }

        Map<Integer, List<String>> ans = new HashMap<>();

        for (String email : emailToID.keySet()) {
            int index = dsu.find(emailToID.get(email));
            ans.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }

        return new ArrayList<>(ans.values());
    }

    private static class DSU {
        int[] parent;

        public DSU() {
            parent = new int[11];
            for (int i = 0; i <= 10; ++i)
                parent[i] = i;
        }

        public int find(int x) {
            return parent[x] == x ? x : find(parent[x]);
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    public static void main(String[] args) {
        List<List<String>> groupedEmails = groupEmails(
                new String[][]{
                        new String[]{"tomhanks@gmail.com", "tomhanks@outlook.com"},
                        new String[]{"th@linkedin.com", "tomhanks@outlook.com"},
                        new String[]{"th@xx.com", "th@linkedin.com"},
                        new String[]{"kay@gmail.com", "kayhu@outlook.com", "kh@xxx.com"},
                        new String[]{"kh@linkedin.com", "kayhu@outlook.com"},
                        new String[]{"nl@gmail.com", "nl@outlook.com"},
                        new String[]{"nl@linkedin.com"}
                }
        );

        System.out.println(groupedEmails);
    }
}
