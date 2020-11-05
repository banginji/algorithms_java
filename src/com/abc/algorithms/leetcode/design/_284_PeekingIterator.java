package com.abc.algorithms.leetcode.design;

import java.util.*;

public class _284_PeekingIterator {
    class PeekingIterator implements Iterator<Integer> {
        Integer lastElem;
        private Iterator<Integer> iterator;

        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
        }

        public Integer peek() {
            if (lastElem == null)
                lastElem = iterator.next();
            return lastElem;
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext() || lastElem != null;
        }

        @Override
        public Integer next() {
            if (lastElem != null) {
                Integer temp = lastElem;
                lastElem = null;
                return temp;
            }
            if (iterator.hasNext()) {
                lastElem = null;
                return iterator.next();
            } else
                return lastElem = null;
        }
    }
}
