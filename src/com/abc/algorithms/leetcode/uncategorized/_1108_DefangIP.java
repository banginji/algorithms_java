package com.abc.algorithms.leetcode.uncategorized;

public class _1108_DefangIP {
    private static String defangIp(String address) {
        String[] parts = address.split("\\.");

        StringBuilder stringBuilder = new StringBuilder();

        for (int idx = 0; idx < parts.length; idx++) {
            stringBuilder.append(parts[idx]);
            if (idx != parts.length - 1)
                stringBuilder.append("[.]");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(defangIp("128.12.43.33"));
    }
}
