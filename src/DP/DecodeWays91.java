package DP;

public class DecodeWays91 {


    // 抓住问题本质？？？
    // 转移方程的优化；
    // 可以用两个变量来store dp matrix, oneBack or twoBack;

//    非常典型的DP问题。dp[i]= dp[i-2] （如果最后两位合法） + dp[i-1] （如果最后一位合法）
//
//    最后两位合法存在，指的是数字在10和26之间；最后一位合法存在，指的是不能为0.


    public int numDecodings(String s) {

        if (s.length() == 0) {return 0;}
        if (s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        int i = 1;
        while (i < s.length()) {
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (s.charAt(i) != '0') {
                dp[i + 1] = dp[i];
            }

            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i + 1] += dp[i - 1];
            }
            i++;
        }
        return dp[s.length()];
    }
}
