package A.DailyPractice;

import java.util.*;

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
            ret.add(stringOf(board));
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
  private List<String> stringOf(boolean[][] board) {
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

    public String getPermutation(int n, int k) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int[] perms = new int[n];
        perms[0] = 1;
        for (int i = 1; i < n; i++) {
            perms[i] = i * perms[i - 1];
        }
        return helper3(n,k - 1, list, perms);
    }

  /**
   * @param n       the total length of string;
   * @param k       k of the kth permutation
   * @param list    the left number to be used (in sorted order)
   * @return        the kth permutation
   */
  private String helper3(int n, int k, List<Integer> list, int[] perms) {
        if (n == 0) {
            return "";
        }
        int perm = perms[n - 1];
        int temp = list.get(k / perm);
        list.remove(k / perm);
        return String.valueOf(temp) + helper3(n - 1, k % perm, list, perms);
    }



    public int[][] generateMatrix(int n) {
      int[][] matrix = new int[n][n];
      int level = n / 2;
        int curr = 0;
        int num = 1;
        while (curr < level) {
          // four directions one by one;
            // curr is the curr level;
            // num is the number to fill;
            for (int i = curr; i < n - curr - 1; i++) {
                matrix[curr][i] = num++;
            }
            for (int i = curr; i < n - curr - 1; i++) {
                matrix[i][n - curr - 1] = num++;
            }
            for (int i = curr; i < n - curr - 1; i++) {
                matrix[n - curr - 1][n - 1 - i] = num++;
            }
            for (int i = curr; i < n - curr - 1; i++) {
                matrix[n - 1 - i][curr] = num++;
            }
        }
        if (n % 2 == 1) {
            matrix[level][level] = num;
        }
        return matrix;
    }

  /**
   * @param matrix the provide matrix;
   * @param target the provide target;
   * @return true if matrix contains target, false ow;
   */
  public boolean searchMatrix(int[][] matrix, int target) {

      int m = matrix.length;
      int n = matrix[0].length;
      int right = m * n - 1;
      int left = 0;

      while (left <= right) {
          int mid = left + (right - left) / 2;
          int i = mid / n;
          int j = mid % n;
          if (matrix[i][j] == target) {
              return true;
          } else if (matrix[i][j] < target) {
              left = mid + 1;
          } else {
              right = mid - 1;
          }
      }
      return false;
  }


  // this is a backtracking to find the solution;
    //

    public boolean exist(char[][] board, String word) {

      for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[0].length; j++) {
              if (bfs(board, i, j, word, 0, new boolean[board.length][board[0].length])) {
                  return true;
              }
          }
      }
      return false;
    }

    final int[][] DIRS = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    private boolean bfs(char[][] board, int i, int j, String word, int start, boolean[][] visited) {
      visited[i][j] = true;
      if (board[i][j] == word.charAt(start)) {
          if (start == word.length() - 1) {
              return true;
          }
          for (int[] dir : DIRS) {
              int x = dir[0] + i;
              int y = dir[1] + j;
              if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && visited[x][y] == false) {
                  if (bfs(board, x, y, word, start + 1, visited)) {
                      return true;
                  }
              }
          }
      }
      return false;
    }


}
