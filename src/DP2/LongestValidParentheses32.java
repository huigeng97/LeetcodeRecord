package DP2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LongestValidParentheses32 {

    // This is a TLE solution,
    // Actually this problem could be solved in O(N);
    // which means it could be solved by one pass (stack or two pointer)


    int max = 0;
    int[][] dp;
    public int longestValidParentheses(String s) {
        dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                Helper(s, i, j);
            }
        }
        return max;
    }

    private boolean Helper(String s, int start, int end) {
        // Quick check;
        if (start == end || (end - start) % 2 == 0) {
            dp[start][end] = -1;
            return false;
        }
        else if (dp[start][end] != 0) {
            return dp[start][end] == 1;
        }
        // Base case;
        else if (start == end - 1) {
            if (s.charAt(start) == '(' && s.charAt(end) == ')') {
                dp[start][end] = 1;
                max = Math.max(max,2);
                return true;
            }
        }
        // Normal case;
        else {
            if (s.charAt(start) == '(' && s.charAt(end) == ')') {
                boolean isValid = Helper(s,start + 1, end - 1);
                for (int i = start + 2; i <= end - 2; i = i + 2) {
                    isValid = isValid || (Helper(s, start, i) && Helper(s, i + 1, end));
                }
                if (isValid) {
                    dp[start][end] = 1;
                    max = Math.max(max,end - start + 1);
                    return true;
                } else {
                    dp[start][end] = -1;
                    return false;
                }
            } else {
                dp[start][end] = -1;
                return false;
            }
        }
        return false;
    }


    /// This problem could be done with one pass;
    public int longestValidParentheses2(String s) {
        int Left = 0;
        int Right = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                Left++;
            } else {
                Right++;
            }

            if (Right > Left) {
                Right = 0;
                Left = 0;
            } else if (Right == Left) {
                max = Math.max(max, Right * 2);
            }
        }
        Left = 0; Right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                Left++;
            } else {
                Right++;
            }

            if (Right < Left) {
                Right = 0;
                Left = 0;
            } else if (Right == Left) {
                max = Math.max(max, Right * 2);
            }
        }
        return max;
    }

    // another way is to use the stack, since it is a parenthesis problem;

    // 核心是什么？ 核心是确保stack.peek（）永远是等于第一个满足的parenthesis的前一个；
    // 也就是说如果
    public int longestValidParentheses3(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                stack.pop();
                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }
        return max;
    }

}
