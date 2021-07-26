package A.DailyPractice;

import java.util.*;

public class Review0725 {
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        int length = words[0].length();
        for (int i = 0; i < s.length() - length * words.length + 1; i++) {
            if (i + length <= s.length() && map.containsKey(s.substring(i, i + length))) {
                // System.out.println(i);
                // System.out.println(map);
                if (helper(s, i, map, length, words.length)) {
                    res.add(i);
                }
            }
        }
        return res;
    }

  /**
   * This helper function will help find if the current start point will satisfy the case;
   * @param s      the string
   * @param start  the start of curr function
   * @param map    the map to memorize the current number of words
   * @param length the length for each word
   * @param count  the number of total words
   * @return       true if we find one solution starting at start;
   */
  private boolean helper(String s, int start, HashMap<String, Integer> map, int length, int count) {

        if (count == 0) {
            return true;
        } else if (start + length > s.length()) {
            return false;
        } else {
            String temp = s.substring(start, start + length);
            if (map.containsKey(temp)) {
                if (map.get(temp) > 0) {
                    map.put(temp, map.get(temp) - 1);
                    if (helper(s, start + length, map, length, count - 1)) {
                        map.put(temp, map.get(temp) + 1);
                        return true;
                    }
                    map.put(temp, map.get(temp) + 1);
                }
            }
        }
        return false;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        final Map<String, Integer> counts = new HashMap<>();
        for (final String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        final List<Integer> indexes = new ArrayList<>();
        final int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i < n - num * len + 1; i++) {
            final Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                final String word = s.substring(i + j * len, i + (j + 1) * len);
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == num) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    // 其实这道题是一道双指针题；

    public String countAndSay(int n) {
        String res = "1";
        while (n > 1) {
            n--;
            StringBuilder sb = new StringBuilder();
//            Stack<Pair> stack = new Stack<>();
//            for (int i = 0; i < res.length(); i++) {
//                char curr = res.charAt(i);
//                if (!stack.isEmpty() && stack.peek().ch == curr) {
//                    stack.peek().count++;
//                } else {
//                    stack.push(new Pair(curr,1));
//                }
//            }
//
//            for (Pair pair : stack) {
//                sb.append(pair.count);
//                sb.append(pair.ch);
//            }
            int count = 1;
            char ch = res.charAt(0);
            for (int i = 1; i < res.length(); i++) {
                if (res.charAt(i) != ch) {
                    sb.append(count);
                    sb.append(ch);
                    count = 1;
                    ch = res.charAt(i);
                } else {
                    count++;
                }
            }
            sb.append(count);
            sb.append(ch);
            res = new String(sb);
        }
        return res;
    }

    class Pair {
        char ch;
        int count;

        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

  /**
   * @param s the word
   * @param p the pattern
   * @return true if s matches the parthen p; false otherwise
   */
  public boolean isMatch(String s, String p) {

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

    public int jump(int[] nums) {

        int dis = nums.length - 1;
        int now = 0;
        int far = 0;
        int jump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            far = Math.max(far, i + nums[i]);
            if (i >= now) {
                jump += 1;
                now = far;
            }
            if (now >= dis) {
                return jump;
            }
        }
        return jump;
    }
}
