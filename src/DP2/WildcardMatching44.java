package DP2;

public class WildcardMatching44 {

    public boolean isMatch2(String s, String p) {

        // use the dp[i][j] to memorize, if s[0,i] matches p[0,j];
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = dp[0][i];
            }
        }

        // update the table dp[i][j];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    // three case;
                    // * match zero letter dp[i + 1][j + 1] = dp[i + 1][j];
                    // * match one letter dp[i + 1][j + 1] = dp[i][j];
                    // * match multiple letter dp[i + 1][j + 1] = dp[i][j + 1];
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j] || dp[i][j + 1];
                }
            }
        }
        // System.out.println(dp[0][0]);
        // System.out.println(dp[1][1]);
        // System.out.println(dp[2][2]);
        return dp[s.length()][p.length()];
    }
    //
    // it is possible to use the special two pointers in the both s and p;
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0, match = 0, starIdx = -1;
        while (i < s.length()) {
            // case 1: match;
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
            }
            // case 2; * found;
            else if (j < p.length() && p.charAt(j) == '*') {
                match = i;
                starIdx = j;
                j++;
            }

            // case 3; not match but we find star;
            // in this case, we need to backtrack the star; match = star
            else if (starIdx != -1) {
                match++;
                i = match;
                j = starIdx + 1;
            }
            // else return false
            else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }
}
