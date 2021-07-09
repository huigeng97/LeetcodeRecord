package DP;

public class BestTimetoBuyandSellStockIII123 {

    /// O(n2) TLE solution

    public int maxProfit(int[] prices) {

        // two times
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, Helper(prices,0,i) + Helper(prices,i+1,prices.length-1));
        }
        return max;
    }

    private int Helper(int[] prices, int start, int end) {
        if (start >= end) {return 0;}
        int max = 0;
        int buy = prices[start];
        for (int i = start + 1; i <= end; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                max = Math.max(max, prices[i]-buy);
            }
        }
        return max;
    }

    // There is a one-pass simulation;
    // simulation is a very good idea to implement some problem;
    // we have four variables for simulation;
    // cost1, cost2, profit1, profit2;
    public int maxProfit1(int[] prices) {
        int t1Cost = Integer.MAX_VALUE,
                t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0,
                t2Profit = 0;

        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);
            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }

        return t2Profit;
    }

    // it could be generalized to K transaction to use variable cost[1:k] , profit[1:k];

    // actually, this is a DP solution;
    // dp[k, j] is the max profit up until prices[j] (Note: NOT ending with prices[j]) using at most k transactions.

    public int maxProfit2(int[] prices) {
        int K = 2;
        int[][] dp = new int[K+1][prices.length];
        int maxProf = 0;
        for (int k = 1; k <= K; k++) {
            int spend = dp[k-1][0] - prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[k][i] = Math.max(dp[k][i-1], prices[i] + spend);
                spend = Math.max(spend, dp[k-1][i] - prices[i]);
                maxProf = Math.max(maxProf, dp[k][i]);
            }
        }
        return maxProf;
    }






}
