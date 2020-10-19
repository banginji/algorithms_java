package com.abc.algorithms.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stack implementation using a List data structure
 */
class Stack {
    List<Integer> elements;

    Stack() {
        this.elements = new ArrayList<>();
    }

    // Adding the incoming new element to the head (i.e at index 0)
    void push(Integer element) {
        this.elements.add(0, element);
    }

    // Removing the element at index 0 if stack is not empty
    void pop() {
        if (this.elements.size() > 0)
            this.elements.remove(0);
    }

    // Increment 'e' elements from the bottom of the stack by k
    void inc(int e, int k) {
        int size = this.elements.size();

        // Calculate the limit i.e the number of elements to increment from the bottom of the stack
        int limit = size - Math.min(e, size);

        // Current index for loop iteration which is set to the index pointing to the bottom of the stack
        int currentIdx = this.elements.size() - 1;

        // Iterate each index from the bottom of the stack 'limit' times and increment the elements
        while (currentIdx >= limit) {
            int currentElement = this.elements.remove(currentIdx);
            this.elements.add(currentIdx, currentElement + k);
            currentIdx--;
        }
    }

    void printStack() {
        // If stack is empty print EMPTY
        if (this.elements.size() == 0) {
            System.out.println("EMPTY");
            return;
        }
        this.elements.forEach(element -> System.out.print(element + " -> "));
        System.out.println();
    }

    public static void main(String[] args) {
        // To read from input
        Scanner scanner = new Scanner(System.in);

        // Obtain the number of operations
        int operationsCount = scanner.nextInt();

        // Initialize an instance of the Stack class
        Stack stack = new Stack();

        // Instantiate a list of allowed operations (Java 9)
        List<String> allowedOperations = List.of("push", "pop", "inc", "print");

        // Operations count checker
        int currentOperation = 0;

        // Loop to ask user input 'operationsCount' times
        while (currentOperation < operationsCount) {
            System.out.println("Enter Command: ");
            Scanner sc = new Scanner(System.in);

            // Read operation from user input
            String operation = sc.nextLine();

            // If invalid operation then prompt user to enter a valid operation
            if (allowedOperations.stream().noneMatch(operation::contains)) {
                System.out.println("Enter a valid operation");
                continue;
            }

            // Push operation
            if (operation.contains("push")) {
                stack.push(Integer.parseInt(operation.split("\\s")[1]));
                System.out.println(stack.elements.get(0));
            }

            // Pop operation
            if (operation.contains("pop")) {
                stack.pop();
                // Check to see if stack is empty
                // If not empty print HEAD and if empty print EMPTY
                if (stack.elements.size() > 0)
                    System.out.println(stack.elements.get(0));
                else
                    System.out.println("EMPTY");
            }

            // Inc operation
            if (operation.contains("inc")) {
                stack.inc(
                        Integer.parseInt(operation.split("\\s")[1]),
                        Integer.parseInt(operation.split("\\s")[2])
                );
                // Check to see if stack is empty
                // If not empty print HEAD and if empty print EMPTY
                if (stack.elements.size() > 0)
                    System.out.println(stack.elements.get(0));
                else
                    System.out.println("EMPTY");
            }

            // Print operation
            if (operation.contains("print"))
                stack.printStack();

            currentOperation++;
        }
    }
}
