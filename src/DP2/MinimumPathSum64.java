package DP2;

public class MinimumPathSum64 {

    public int minPathSum(int[][] grid) {


        // dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int j = 1; j < grid[0].length; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            dp[0] = grid[i][0] + dp[0];
            for (int j = 1; j < grid[0].length; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[grid[0].length - 1];
    }

    // the space can also be used by 1D by dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
}
