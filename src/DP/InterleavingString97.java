package DP;

public class InterleavingString97 {
    public boolean isInterleave(String s1, String s2, String s3) {

        // use the backtracking first,
        // then try to use the DP to optimize;
        if (s2.length() == 0 && s1.length() == 0) {
            return s3.length() == 0;
        }
        if (s1.length() + s2.length() != s3.length()) { return  false;}
        int start1 = 0;
        int start2 = 0;
        if (s1.length() > 0 && s3.charAt(0) == s1.charAt(0)) {
            if (isInterleave(s1.substring(1), s2, s3.substring(1))) {
                return true;
            }
        }
        if (s2.length() > 0 && s3.charAt(0) == s2.charAt(0)) {
            if (isInterleave(s1, s2.substring(1), s3.substring(1))) {
                return true;
            }
        }
        return false;
    }

    int[][] memo;
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) { return  false;}
        memo = new int[s1.length()][s2.length()];
        return helper(s1,s2,s3,0,0, 0);
    }

    private boolean helper(String s1, String s2, String s3, int start1, int start2, int start3) {

        if (memo[start1][start2] != 0) {
            return memo[start1][start2] == 1;
        }
        if (start3 == s3.length()) {return true;}

        if (start1 < s1.length() && s3.charAt(start3) == s1.charAt(start1)) {
            if (helper(s1, s2, s3, start1 + 1, start2, start3 + 1)) {
                memo[start1][start2] = 1;
                return true;
            } else {
                memo[start1][start2] = -1;
            }
        }
        if (start2 < s2.length() && s3.charAt(start3) == s2.charAt(start2)) {
            if (helper(s1, s2, s3, start1, start2 + 1, start3 + 1)) {
                memo[start1][start2] = 1;
                return true;
            } else {
                memo[start1][start2] = -1;
            }
        }
        memo[start1][start2] = -1;
        return false;
    }


    public boolean isInterleave3(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                if (i == 0) {
                    dp[i][j] = s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if (j == 0) {
                    dp[i][j] = s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] || dp[i - 1][j])
                            && (s2.charAt(j - 1) == s3.charAt(i + j -2) || s1.charAt(i - 1) == s3.charAt(i + j -2));
                }
            }
        }
        return dp[len1][len2];
    }



    public static void main(String[] args) {
        //
      InterleavingString97 test = new InterleavingString97();
      System.out.println(test.isInterleave("aabcc","dbbca","aadbbcbcac"));
  }
}
