package Stack;

import java.util.Stack;

public class LargestRectangleinMatrix85 {
    // this question is to find the max rectangle in a 2D matrix;
    // what we could do is to change the 2d problem into a 1D problem and
    // solve the problem largest rectangle in histogram;
    // to solve it
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] dp = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[j] = (matrix[i][j] == '1') ? dp[j] + 1 : 0;
            }
            max = Math.max(max, MaxRecArray(dp));
        }
        return max;
    }

    // This comes to the problem 84;
    // to solve it use the increasing stack;
    // first we pop the target element when current element is not larger than current one;
    // the left side bound is the stack.peek, where is the first element that smaller than target one;
    //
    private int MaxRecArray(int[] dp) {
        int[] res = new int[dp.length];
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < dp.length; i++) {
            while (stack.peek() != -1 && dp[i] <= dp[stack.peek()]) {
                int index = stack.pop();
                max = Math.max(max, dp[index] * (i - stack.peek() -1));
            }
            stack.push(i);
        }

        // if an element is still in the stack, means it could go to the right side;
        while (stack.peek() != -1 ) {
            int index = stack.pop();
            max = Math.max(max, dp[index] * (dp.length-stack.peek() - 1));
        }
        return max;
    }
}
