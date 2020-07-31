package com.abc.algorithms.leetcode.string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _227_BasicCalculator {
    private static String calculate(String s) throws NotAValidOperatorException {
        Pattern pattern = Pattern.compile("\\d+|-\\d+|[/*+]");
        Matcher matcher = pattern.matcher(s);

        Queue<String> queue = new LinkedList<>();

        while (matcher.find()) {
            String num = matcher.group();
            try {
                if (Integer.parseInt(num) < 0) queue.offer("+");
            } catch (NumberFormatException e) {
                queue.offer(num);
                continue;
            }
            queue.offer(num);
        }

        List<String> operators = List.of("/", "*", "+");

        for (String operator : operators) {
            itrCalculation(queue, operator);
        }

        return queue.remove();
    }

    private static void itrCalculation(Queue<String> queue, String currentOperator) throws NotAValidOperatorException {
        boolean shouldCalculate = false;

        Deque<String> deque = new LinkedList<>();

        while (!queue.isEmpty()) {
            if (shouldCalculate) {
                String operator = deque.removeLast();
                String operandOne = deque.removeLast();
                deque.offer(String.valueOf(calc(operandOne, queue.remove(), operator)));
                shouldCalculate = false;
                continue;
            }

            if (queue.peek().equals(currentOperator)) {
                shouldCalculate = true;
                deque.offer(queue.remove());
                continue;
            }

            deque.offer(queue.remove());
        }

        while(!deque.isEmpty())
            queue.offer(deque.remove());
    }

    private static int calc(String a, String b, String operator) throws NotAValidOperatorException {
        return switch (operator) {
            case "/" -> Integer.parseInt(a) / Integer.parseInt(b);
            case "*" -> Integer.parseInt(a) * Integer.parseInt(b);
            case "+" -> Integer.parseInt(a) + Integer.parseInt(b);
            default -> throw new NotAValidOperatorException();
        };
    }

    private static class NotAValidOperatorException extends Exception {
        public NotAValidOperatorException() {
            super("Not a valid operator");
        }
    }

    public static void main(String[] args) throws NotAValidOperatorException {
        System.out.println(calculate("1+2*5/3+6/4*2"));
    }
}
