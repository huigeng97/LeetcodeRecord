package DP;

public class ClimbingStairs70 {
    // time complexity O(n); space complexity O(n);
    // it is possible to reduce space complexity to O(1);
    // use two variable to replace dp[i-1], dp[i-2];

    public int climbStairs(int n) {

        if (n == 0) {return 0;}
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[0] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
