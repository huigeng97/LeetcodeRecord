package BackTracking;

/**
 * @author Geng Hui
 */
public class Test10 {

    // 有一点dp的意思p,substring,j,对应s的substring k;


    public boolean isMatch(String s, String p) {

       boolean[][] dp = new boolean[s.length()+1][p.length()+1];
       dp[0][0] = true;
        for(int j = 2; j <= p.length(); j ++){
            if(p.charAt(j-1) == '*' && dp[0][j-2]){
                dp[0][j] = true;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j-1) == '.' || s.charAt(i-1) == p.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                if (p.charAt(j-1) == '*') {
                    if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        dp[i][j] = (dp[i-1][j] ||
                                // in this case, a* counts as multiple a // to understand this, use the recursive function
                                // to iterate next several cases;
                                dp[i][j-1] ||
                                // in this case, a* counts as single a
                                dp[i][j-2]);
                        // in this case, a* counts as empty
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
       return dp[s.length()][p.length()];
    }
}
