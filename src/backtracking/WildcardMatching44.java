package backtracking;

/**
 * @author Geng Hui
 */

public class WildcardMatching44 {

    public boolean isMatch(String s, String p) {

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i-1] && (p.charAt(i-1) == '*');
        }

        // start with the dp;
        // two states: ? or equals
        // *; dp[i][j] == dp[i-1][j]; zero, one or multiple
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j-1] ||
                            // one; equals to ?;
                            dp[i-1][j] ||
                            dp[i][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /// this question is better to solve by backtracking than the dp;
    // because:
    // 回溯法： 但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，
    // 而满足回溯条件的某个状态的点称为“回溯点”。



    public boolean isMatch2(String s, String p) {
        // backtracking

        int starId = -1;
        int stampId = -1;
        int sLen = s.length();
        int sId = 0;
        int pId = 0;
        int pLen = p.length();

        while (sId < sLen) {
                if (pId < pLen && (s.charAt(sId) == p.charAt(pId) || p.charAt(pId) == '?')) {
                    sId++;
                    pId++;
                }
                else if (pId < pLen && p.charAt(pId) == '*') {
                    // initialize with zero matching;
                    starId = pId;
                    stampId = sId;
                    pId++;
                } else if (starId == -1) {
                    return false;
                } else {
                    pId = starId + 1;
                    sId = stampId + 1;
                    stampId = sId;
                }
        }

        for (int i = pId; i < pLen; i++) {
            if (p.charAt(i) != '*') {return false;}
        }

        return true;
    }
}
