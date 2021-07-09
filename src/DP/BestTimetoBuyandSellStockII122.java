package DP;

public class BestTimetoBuyandSellStockII122 {
    public int maxProfit(int[] prices) {

        int curr = 0;
        for (int i = 1; i < prices.length; i++) {
            curr += Math.max(0, prices[i] - prices[i-1]);
        }
        return curr;
    }
}
