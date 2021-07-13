package DP2;

public class CoinChangeII518 {

    public int change(int amount, int[] coins) {

        int[] dp = new int[amount+1];
        for (int coin : coins) {
            dp[coin] = 1;
        }

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }
//
    // This solution is correct, but the upper one is not;
    // The lower one is the combination number of the amount, upper is the permutation;

    public int change2(int amount, int[] coins) {

        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins){
            for (int i = coin; i <= amount; i++) {
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }
}
