package DP;

public class MaximalSquare221 {

    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i+1][j+1] = Math.min(Math.min(dp[i][j], dp[i][j+1]), dp[i+1][j]) + 1;
                    max = Math.max(max, dp[i+1][j+1]);
                }

            }
        }
        return max * max;
    }

    public int maximalSquare2(char[][] matrix) {
        int[] new_dp = new int[matrix[0].length+1];
        int[] old_dp = new int[matrix[0].length+1];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    new_dp[j+1] = Math.min(Math.min(new_dp[j], old_dp[j+1]), old_dp[j]) + 1;
                    max = Math.max(max, new_dp[j+1]);
                }
            }
            old_dp = new_dp;
            new_dp = new int[matrix[0].length+1];
        }
        return max * max;
    }

    // this problem could also be solved by monotonic stack
    // which is pretty similar with the P85, the only change is the res = Math.max(res, Math.min(Height, i - stack.peek() - 1));
}
