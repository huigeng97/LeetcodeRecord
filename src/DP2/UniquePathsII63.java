package DP2;

public class UniquePathsII63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {


        // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        int[] dp = new int[obstacleGrid[0].length];

        for (int i = 0; i < obstacleGrid.length; i++) {
            if (i == 0) {
                dp[0] = 1;
            }
            if (obstacleGrid[i][0] == 1) {
                dp[0] = 0;
            }
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[j] = dp[j] + dp[j - 1];
                } else {
                    dp[j] = 0;
                }
            }
        }
        return dp[dp.length - 1];
    }
}
