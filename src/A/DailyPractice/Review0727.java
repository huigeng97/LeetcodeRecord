package A.DailyPractice;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class Review0727 {
    public boolean isInterleave2(String s1, String s2, String s3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // curr is the pos of s3;
        int curr = 0;
        // p is the pos of s1;
        int pos1 = 0;
        int pos2 = 0;
        Integer backPos1 = null;
        Integer backCurr = null;
        // curr - p is the (length) next pos of s2 (not start);
        int length = s3.length();
        while (curr < length) {
            pos2 = curr - pos1;
            if (pos2 < s2.length() && s3.charAt(curr) == s2.charAt(pos2)) {
                if (backPos1 == null) {
                    backPos1 = pos1;
                    backCurr = curr;
                }
            }
            if (pos1 < s1.length() && s3.charAt(curr) == s1.charAt(pos1)) {
                curr++;
                pos1++;
            } else if (pos2 < s2.length() && s3.charAt(curr) == s2.charAt(pos2)) {
                curr++;
                pos2++;
            } else {
                if (backPos1 != null) {
                    pos1 = backPos1;
                    curr = backCurr;
                    curr++;
                    backPos1 = null;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // 不要太激进

    public boolean isInterleave(String s1, String s2, String s3) {

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s3.charAt(i)) {
                dp[i + 1][0] = true;
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == s3.charAt(i)) {
                dp[0][i + 1] = true;
            }
        }

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                dp[i + 1][j + 1] = (s1.charAt(i) == s3.charAt(i + j + 1)) ? dp[i][j + 1] :
                        (s2.charAt(j) == s3.charAt(i + j + 1)) && dp[i + 1][j];
            }
        }
        return dp[s1.length()][s2.length()];
    }


  public static void main(String[] args) {

    //
      Review0727 test = new Review0727();
      System.out.println(test.isInterleave("aabd","abdc","aabdabcd"));
  }

  // four ops: 1 insert; 2 delete 3 replace 4 gap;
    public int minDistance(String word1, String word2) {

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        dp[0][0] = 0;
        for (int i = 0; i < word1.length(); i++) {
            dp[i + 1][0] = i + 1;
        }

        for (int i = 0; i < word2.length(); i++) {
            dp[0][i + 1] = i + 1;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {

                dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1])) + 1;
                if (word1.charAt(i) == word1.charAt(j)) {
                    dp[i + 1][j + 1] = Math.min(dp[i][j], dp[i + 1][j + 1]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public String simplifyPath(String path) {

        String[] dirs = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 1; i < dirs.length; i++) {
            if (dirs[i].equals(".") || dirs[i].equals("")) {
                continue;
            } else if (dirs[i].equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dirs[i]);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        String newPath = "";
        for (String dir : stack) {
            newPath += "/" + dir;
        }
        return newPath;
    }

}
