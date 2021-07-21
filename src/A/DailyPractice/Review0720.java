package A.DailyPractice;

public class Review0720 {

    public boolean isMatch(String s, String p) {

        // this problem could be solved by backtracking
        // and could be optimized by DP;

        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];

        dp[0][0] = true;
        for (int j = 1; j < pLen; j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j - 1];
            }
        }

        // start to do the backtracking at here;
        // if s chatat i == p chat at j; then dp[i + 1][j + 1] = dp[i][j];
        // if p chatat j == '.'; then dp[i + 1][j + 1] = dp[i][j];
        // if p chatat j == '*'; then three cases: zero, one, multiple;
        // zero : dp[i + 1][j + 1] = dp[i + 1][j - 1];
        // one : dp[i + 1][j + 1] = dp[i + 1][j];
        // multiple: if (s.chat at i == p.chatat j) dp[i + 1][j + 1] = dp[i][j + 1];

        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    boolean prev = s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.';
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || (prev && dp[i][j + 1]);
                }
            }
        }
        return dp[sLen][pLen];
    }


}
