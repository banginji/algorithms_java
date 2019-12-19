package com.abc.algorithms.designpattern;

public class BuilderPattern {
    private static class State {
        private final int[] coins;
        private final int currentCoin;
        private final int count;
        private final int remainingSum;

        State(Builder builder) {
            this.coins = builder.coins;
            this.currentCoin = builder.currentCoin;
            this.count = builder.count;
            this.remainingSum = builder.remainingSum;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private int[] coins;
            private int currentCoin;
            private int count;
            private int remainingSum;

            public void setCoins(int[] coins) {
                this.coins = coins;
            }

            public Builder setCurrentCoin(int currentCoin) {
                this.currentCoin = currentCoin;
                return this;
            }

            public Builder setCount(int count) {
                this.count = count;
                return this;
            }

            public Builder setRemainingSum(int remainingSum) {
                this.remainingSum = remainingSum;
                return this;
            }

            public State build() {
                return new State(this);
            }
        }

        public int[] getCoins() {
            return coins;
        }

        public int getCurrentCoin() {
            return currentCoin;
        }

        public int getCount() {
            return count;
        }

        public int getRemainingSum() {
            return remainingSum;
        }
    }
}
