package DP;

public class UniquePaths62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i+1][j+1] = 1;
                } else {
                    dp[i+1][j+1] = dp[i][j+1] + dp[i+1][j];
                }
            }
        }
        return dp[m][n];
    }
}
