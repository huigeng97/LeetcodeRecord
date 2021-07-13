package DP2;

public class LongestPalindromicSubstring5 {
    // First start with the O(N2) method
    String max = "";
    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            check(s,i);
        }
        return max;
    }

    // center or left of the center;
    private void check(String s, int center) {
        int i = 0;
        while (center - i >= 0 && center + i < s.length()) {
            if (s.charAt(center - i) == s.charAt(center + i)) {
                if (2 * i + 1 > max.length()) {
                    System.out.println("first" + i);
                    max = s.substring(center - i, center + i + 1);
                }
            } else {
                break;
            }
            i++;
        }
        i = 0;
        if (center + 1 < s.length() && s.charAt(center) == s.charAt(center + 1)) {
            System.out.println("second" + i);
            while (center - i >= 0 && center + i + 1 < s.length()) {
                if (s.charAt(center - i) == s.charAt(center + i + 1)) {
                    if (2 * i + 2 > max.length()) {
                        max = s.substring(center - i, center + i + 2);
                    }
                } else {
                    break;
                }
                i++;
            }
        }
        return;
    }


    // Actually this one is more precise,
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }



    // The DP solution;

    // P(i,j) = True if P(i+1, j-1) == True and s.charAt(i) == s.charAt(j);

    // baseCase
    public String longestPalindrome2(String s) {

        String res = "";
        String test = String.join("#",s);

        if (s == null || s.length() == 0) {
            return res;
        }
        res = s.substring(0,1);
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = true;
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (2 > res.length()) {
                    res = s.substring(i,i + 2);
                }
            }
            for (int j = i + 2; j < s.length(); j++) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i,j + 1);
                }
            }
        }
        return res;
    }

      // There is a method called Manacher Algo; which is beyond the scope;
      public String longestPalindrome3(String s) {
        return "";
      }




      public static void main(String[] args) {
        // this is a quick method add the # in the word;
          String s = "apple";
          String test = s.replace("", "#");
          System.out.println(test);
      }
}
