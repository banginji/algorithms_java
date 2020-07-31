package com.abc.algorithms.misc;

public class Stock {
    private static class DayPrice {
        int day;
        int price;

        DayPrice(int day, int price) {
            this.day = day;
            this.price = price;
        }

        public int getDay() {
            return day;
        }

        public int getPrice() {
            return price;
        }
    }

    /**
     * Calculate the pair of days for buying and selling stocks which will maximize the profit
     * @param dayPrices - array of DayPrice
     * @return - returns a string of the format BUY[DAY]SELL[DAY]
     */
    private String maxProfit(DayPrice[] dayPrices) {
        StringBuilder sb = new StringBuilder();

        // Initialize initial profit to minimum value
        int profit = Integer.MIN_VALUE;

        // Vars to track buy and sell day indices
        int firstIdx = 0, secondIdx = 0;

        // Nested loop to find the pair of days
        for (int iIdx = 0; iIdx < dayPrices.length; iIdx++) {
            for (int jIdx = iIdx; jIdx < dayPrices.length; jIdx++) {
                // Current profit for BUY on day in iIdx index and SALE on day in jIdx index in dayPrices array
                int currentProfit = dayPrices[jIdx].getPrice() - dayPrices[iIdx].getPrice();
                // If profit is negative ignore and continue in the inner loop
                if (currentProfit < 0)
                    continue;
                // If current profit is greater than tracked profit (var profit) then update indices
                if (profit < currentProfit) {
                    profit = currentProfit;
                    firstIdx = iIdx;
                    secondIdx = jIdx;
                }
            }
        }

        return sb
                .append("BUY")
                .append("[")
                .append(dayPrices[firstIdx].getDay())
                .append("]")
                .append("SELL")
                .append("[")
                .append(dayPrices[secondIdx].getDay())
                .append("]")
                .toString();
    }

    public static void main(String[] args) {
        DayPrice[] dayPrices = new DayPrice[] {
                new DayPrice(0, 5),
                new DayPrice(1, 9),
                new DayPrice(2, 3),
                new DayPrice(3, 5),
                new DayPrice(4, 8),
                new DayPrice(5, 7)
        };

        Stock stock = new Stock();
        System.out.println(stock.maxProfit(dayPrices));
    }
}
