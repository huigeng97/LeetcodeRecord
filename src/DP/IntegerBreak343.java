package DP;

public class IntegerBreak343 {

    int[] dp = new int[59];
    public int integerBreak(int n) {
        if (n == 1) {
            dp[n] = 1;
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int max = 0;
        for (int i = 1; i <= n/2; i++) {
            int temp = Math.max(i,integerBreak(i)) * Math.max(integerBreak(n-i), n-i);
            max = Math.max(temp, max);
        }
        dp[n] = max;
        return max;
    }

    // others use the math method like max product = 2^a * 3^b; where a = 0, 1 or 2, depends on n % 3 == 0, 2, 1;
    // O(log(n));
}
