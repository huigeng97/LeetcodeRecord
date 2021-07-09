package DP;

public class BestTimetoBuyandSellStockIV188 {

    // 小问题搞清楚然后才能搞大问题， 如果没有搞清楚小问题，直接搞大问题，可能最后也是一塌糊涂；
    // 这道题应该专注于最基本的问题，121，搞清楚如果用DP 如何用Kadane's algo，然后再generalization;

    public int maxProfit(int K, int[] prices) {
        if (prices.length <= 1) {return 0;}
        int[][] dp = new int[K+1][prices.length];
        int maxProf = 0;
        for (int k = 1; k <= K; k++) {
            int spend = dp[k-1][0] - prices[0];
            System.out.println(spend);
            for (int i = 1; i < prices.length; i++) {
                dp[k][i] = Math.max(dp[k][i-1], prices[i] + spend);
                spend = Math.max(spend, dp[k-1][i] - prices[i]);
                maxProf = Math.max(maxProf, dp[k][i]);
            }
        }
        return maxProf;
    }

    // here is another solution with the Kadane's algo;

    // profit record the max profit
    public int maxProfit2(int K, int[] prices) {


        int max = 0;
        if (K * 2 >= prices.length) {
            for (int j = 1; j < prices.length; j++) {
                max += Math.max(0,prices[j] - prices[j-1]);
            }
            return max;
        }

        int[] profit = new int[prices.length];
        for (int i = 0; i < K; i++) {
            int val = 0;
            for (int j = 1; j < prices.length; j++) {
                val = Math.max(val + prices[j] - prices[j - 1], profit[j]);
                profit[j] = Math.max(profit[j-1],val);
            }
        }

        int k = K;
        int len = prices.length;
        int[][] profit2 = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmCost =  -prices[0];
            for (int j = 1; j < len; j++) {
                profit2[i][j] = Math.max(profit2[i][j - 1], prices[j] + tmCost);
                tmCost =  Math.max(tmCost, profit2[i - 1][j - 1] - prices[j]);
            }
        }

        return profit[profit.length-1];
    }

  public static void main(String[] args) {

    //
      BestTimetoBuyandSellStockIV188 test = new BestTimetoBuyandSellStockIV188();
      test.maxProfit2(2, new int[]{3,2,6,5,0,3,8});
  }
}
