package A.DailyPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Review0726 {
    int res = 0;
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {

        boolean[] visited = new boolean[students.length];
        helper(students, 0, mentors, visited, 0);
        return res;
    }

    /**
     * @param students the students
     * @param start        the current index of students
     * @param mentors  the mentors
     * @param visited  a boolean array to check if a mentor is paired;
     * @return    nothing?
     */
    private void helper(int[][] students, int start, int[][] mentors, boolean[] visited, int score) {
        if (start == students.length) {
            res = Math.max(res, score);
            return;
        }

        // backtracking;
        for (int i = 0; i < mentors.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                helper(students, start + 1, mentors, visited, score + calScore(students[start], mentors[i]));
                visited[i] = false;
            }
        }
    }

    private int calScore(int[] a, int[] b){
        int count = 0;

        for(int i = 0; i < b.length; i++) {
            if(a[i] == b[i]) {
                count += 1;
            }
        }
        return count;
    }


    public int getLucky(String s, int k) {

        int num = 0;
        for (char ch : s.toCharArray()) {
            int i = ch - 'a' + 1;
            num += i / 10 + i % 10;
        }

        for (int i = 0; i < k - 1; i++) {
            System.out.println(num);
            int start = num;
            num = 0;
            while (start != 0) {
                num += start % 10;
                start = start / 10;
            }
        }
        return num;
    }

    List<List<String>> ret;
  /**
   * @param n   the size of the board;
   * @return    all the valid chess position
   */
  public List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        helper2(board, 0, n);
        // this problem should be done with backtracking;
        return ret;
    }

  /**
   * @param board  the board to memorize if board[i][j] has the queen;
   * @param line   the curr line under bactractacking
   * @param n      the size of the board;
   */
  private void helper2(boolean[][] board, int line, int n) {
        if (line >= n) {
            ret.add(StringOf(board));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isValid(board, line, j)) {
                board[line][j] = true;
                helper2(board, line + 1, n);
                board[line][j] = false;
            }
        }
    }

  /**
   * this function is to check if the current position is valid
   * @param board the board to memorize if board[i][j] has the queen;
   * @param line  the curr line under bactractacking
   * @param j     the curr column under bactracking
   * @return      true if valid, false ow;
   */
  private boolean isValid(boolean[][] board, int line, int j) {
        // only care about the previous line, three direction, (-1,-1), (-1,0), (-1, 1);
        for (int i = 0; i < line; i++) {
            boolean b1 = board[i][j];
            boolean b2 = (line - i + j < board.length)  ? board[i][line - i + j] : false;
            boolean b3 = (j - (line - i) >= 0) ? board[i][j - (line - i)] : false;
            if (b1 || b2 || b3) {
                return false;
            }
        }
        return true;
    }

  /**
   * this function is to convert the boalean 2D array into a list of string format;
   * @param board the board to memorize if board[i][j] has the queen;
   * @return      a string format of the board;
   */
  private List<String> StringOf(boolean[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String temp = "";
            for (int j = 0; j < board.length; j++) {
                temp += board[i][j] == true ? "Q" : ".";
            }
            res.add(temp);
        }
        return res;
    }

  // another solution would be using three while loop to do this;

  /**
   * @param intervals   the original intervals;
   * @param newInterval the ready to insert interval;
   * @return            the update intervals;
   */
  public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            if (newInterval == null || interval[0] > newInterval[1]) {
                if (newInterval != null) {
                    list.add(newInterval);
                    newInterval = null;
                }
                list.add(interval);
            } else if (newInterval != null && interval[1] < newInterval[0]) {

                list.add(interval);
            } else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1]  = Math.max(newInterval[1], interval[1]);
            }
        }
        if (newInterval != null) {
            list.add(newInterval);
        }
        return list.toArray(new int[list.size()][2]);
    }


}
