package DP;

public class BestTimetoBuyandSellStock121 {

    public int maxProfit(int[] prices) {

        int max = 0;
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                max = Math.max(prices[i] - buy, max);
            }
        }
        return max;
    }

    //Kadane's Algorithm
    // sum the curr together until it is negative;
    // track the maxprofit at the same time;

    public int maxProfit2(int[] prices) {

        int max = 0;
        int curr = 0;
        for (int i = 1; i < prices.length; i++) {
            curr = Math.max(0, curr += prices[i] - prices[i-1]);
            max = Math.max(curr, max);
        }
        return max;
    }
}
