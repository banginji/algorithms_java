package com.abc.algorithms.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _1169_InvalidTransactions {
    private static List<String> invalidTransactions(String[] transactions) {
        List<String> invalidTxns = new ArrayList<>();

        for (int outerIdx = 0; outerIdx < transactions.length; outerIdx++) {
            String[] txnDetails = transactions[outerIdx].split(",");

            if (Integer.parseInt(txnDetails[2]) > 1000) {
                invalidTxns.add(transactions[outerIdx]);
                continue;
            }

            for (int innerIdx = 0; innerIdx < transactions.length; innerIdx++) {
                if (innerIdx == outerIdx)
                    continue;
                String[] itrTxnDetails = transactions[innerIdx].split(",");

                if (
                        txnDetails[0].equals(itrTxnDetails[0])
                                && !txnDetails[3].equals(itrTxnDetails[3])
                                && Math.abs(
                                Integer.parseInt(txnDetails[1]) -
                                        Integer.parseInt(itrTxnDetails[1])
                        ) <= 60
                )
                    invalidTxns.add(transactions[outerIdx]);
            }
        }
        return invalidTxns;
    }

    public static void main(String[] args) {
        System.out.println(
                invalidTransactions(new String[]{
                        "alice,20,800,mtv", "alice,50,100,beijing"
//                        "bob,689,1910,barcelona","alex,696,122,bangkok","bob,832,1726,barcelona","bob,820,596,bangkok","chalicefy,217,669,barcelona","bob,175,221,amsterdam"
                })
        );
    }
}
