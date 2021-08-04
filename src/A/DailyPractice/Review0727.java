package A.DailyPractice;

import BinaryTree.TreeNode;

import java.util.*;
import LinkedList.*;

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

    public void recoverTree(TreeNode root) {

        // in order travese;
        // get the series and once the next is larger than pre;
        // we get the first node;
        TreeNode first = null;
        TreeNode second = null;
        // DFS with the stack;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.poll();

            // Do something:

            if (prev != null) {
                if (prev.val > curr.val) {
                    if (first == null) {
                        first = prev;
                        // System.out.println("find first" + first.val);
                    }
                    second = curr;
                    // System.out.println("find second" + curr.val);
                }
            }
            prev = curr;

            if (curr.right != null) {
                curr = curr.right;
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }


    public int largestRectangleArea(int[] heights) {

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                int temp = stack.poll();
                max = Math.max(max, heights[temp] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int temp = stack.poll();
            max = Math.max(max, heights[temp] * (heights.length - stack.peek() - 1));
        }
        return max;
    }

    public void setZeroes(int[][] matrix) {

        boolean firstCol = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
            }
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            for (int i = 1; i < matrix.length; i++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

        }

        if (firstCol) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode front = head;
        ListNode prev = head;
        ListNode dummy = new ListNode();
        ListNode end = dummy;
        end.next = head;
        while (front != null) {
            if (front.val < x ) {
                // move the front to the end;
                if (front == end.next) {
                    prev = front;
                    front = front.next;
                    end = end.next;
                } else {
                    ListNode move = front;
                    front = front.next;
                    prev.next = front;
                    // connect the prev to the front.next;;
                    ListNode temp = end.next;
                    end.next = move;
                    move.next = temp;
                    end = end.next;
                }
            } else {
                prev = front;
                front = front.next;
            }
        }
        return  dummy.next;
    }

    public int maximalRectangle(char[][] matrix) {

        int max = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            max = Math.max(max, findMaxRectangle(heights));
        }
        return max;
    }

    private int findMaxRectangle(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                int temp = stack.poll();
                max = Math.max(max, heights[temp] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int temp = stack.poll();
            max = Math.max(max, heights[temp] * (heights.length - stack.peek() - 1));
        }
        return max;
    }

    public boolean isNumber(String s) {
        // start with a sign of (+ or -);
        // then follow by a decimal number ;
        // then could be follow by a "e or e";
        // then could be follow by a integer;
        String[] numbers = s.split("e|E");
        System.out.println(numbers.length);
        if (numbers.length > 2) {
            return false;
        } else {
            if (!isDecimal(numbers[0])) {
                return false;
            }
            if (numbers.length == 2) {
                if (!isInteger(numbers[1])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDecimal(String number) {
        String[] numbers = number.split(".");
        if (numbers.length > 2) {
            return false;
        } else {
            if (!isInteger(numbers[0])) {
                return false;
            }
            if (numbers.length == 2) {
                for (char ch : numbers[1].toCharArray()) {
                    if (!Character.isDigit(ch)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isInteger(String number) {
        if (number.charAt(0) == '+' || number.charAt(0) == '-') {
            number = number.substring(1);
        }
        for (char ch : number.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }


    public boolean isNumber2(String s)
    {
        if (s.equals("")) {
            return false;
        }

        int countE = 0, posE = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E')
            {
                countE++;
                posE = i;
            }
        }
        if (countE > 1) {
            return false;
        }
        if (countE == 0) {
            return isOK(s, 1);
        }
        return isOK(s.substring(0, posE), 1) && isOK(s.substring(posE + 1), 0);
    }

    public boolean isOK(String s, int k)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if ((s.charAt(i) == '+' || s.charAt(i) == '-') && i != 0) {
                return false;
            }
        }
        if (s.equals("") || s.equals(".")) return false;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            return isOK(s.substring(1), k);
        }
        int countDot = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '.') {
                countDot++;
            } else if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return countDot <= k;
    }

  public static void main(String[] args) {

    String s = "e03";
        System.out.println(s.split("e|E").length);
  }
}
