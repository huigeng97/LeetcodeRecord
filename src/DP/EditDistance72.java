package DP;

public class EditDistance72 {


    // First it is clear this is a DP problem;
    // There are four different operation;
    // insert, delete, replace, skip;
    // always operate on the word1;
    // insert : dp[i][j] =  1 + dp[i][j-1];
    // delete : dp[i][j] =  1 + dp[i-1][j];
    // replace : dp[i][j] = 1 + dp[i-1][j-1];
    // skip: dp[i][j] = dp[i-1][j-1];

    public int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(1 + dp[i][j-1], 1 + dp[i-1][j]), 1 + dp[i-1][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }


    // More faster , Less space;
    public int minDistance2(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[2][len2 + 1];
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;

        }

        for (int i = 1; i <= len1; i++) {
            dp[1] = new int[len2 + 1];
            dp[1][0] = i;
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[1][j] = dp[0][j - 1];
                } else {
                    dp[1][j] = Math.min(Math.min(1 + dp[1][j-1], 1 + dp[0][j]), 1 + dp[0][j-1]);
                }
            }
            dp[0] = dp[1];
        }
        return dp[0][len2];
    }
}
