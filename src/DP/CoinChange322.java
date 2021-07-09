package DP;

import java.util.Arrays;

/**
 * @author Geng Hui
 */
public class CoinChange322 {
    int[] dp = new int[10001];
    public int coinChange(int[] coins, int amount) {

        // if dp[amount - coins[i] > 0;
        // dp = Math.min(dp[amount - coins[i]) + 1;
        // if dp == 0; dp == -1;
        if (amount < 0) {return -1;}
        if (amount == 0) {return 0;}

        if (dp[amount] != 0) {return dp[amount];}

        int min = Integer.MAX_VALUE;;
        for (int i = coins.length-1; i >= 0; i--) {
            int count = coinChange(coins,amount-coins[i]);
            if (count >= 0) {
                min = Math.min(min,count);
            }
        }

        if (min != Integer.MAX_VALUE) {
            dp[amount] = min + 1;
        } else {
            dp[amount] = -1;
        }

        return dp[amount];
    }

    // This is what I expect;
    // iterative all the combination not permutation;

    public int coinChange2(int[] coins, int amount) {
        int[] A = new int[amount+1];
        Arrays.fill(A,amount+1);
        A[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                A[i] = Math.min(A[i], A[i - coin] + 1);
            }
        }
        return A[amount] > amount ? -1 : A[amount];
    }

}
