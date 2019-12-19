package com.abc.algorithms.misc;

public class Tuple<T> {
    private final T first;
    private final T second;

    public Tuple(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return second;
    }
}
