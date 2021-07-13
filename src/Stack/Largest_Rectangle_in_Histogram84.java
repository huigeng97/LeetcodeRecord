package Stack;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Largest_Rectangle_in_Histogram84 {
    // this is not easy to look at what the question is, but it is a monotone stack problem;
    // we need to find next number of the larger or equal to number;
    // the stack pop when we find a smaller number, so we use the increasing stack
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int[] nums = new int[heights.length];
        int[] nums2 = new int[heights.length];
        for (int i = 0; i<heights.length;i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()] ) {
                System.out.println(i);
                int index = stack.pop();
                nums[index] = i - index;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            nums[index] = heights.length - index;
        }

        stack = new Stack<>();
        for (int i = heights.length -1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()] ) {
                System.out.println(i);
                int index = stack.pop();
                nums2[index] = index - i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            nums2[index] = index + 1;
        }
        int max = 0;
        for (int i = 0; i<heights.length;i++) {
            System.out.println(nums[i]);
            max = Math.max(max, heights[i]*(nums[i] + nums2[i] - 1));
        }
        return max;
    }


    /// Slightly change, we are not only want to find the right, but also want to find the left;
    /// The stack[top - 1] is the first left element that is "strictly" smaller than the top element;
    /// The stack.pop() occur when the first right larger element that we occur;
    /// Therefore, the length is the length for index = stack.pop() is i - stack(peek) + 1;
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int[] nums = new int[heights.length];
        for (int i = 0; i<heights.length;i++) {
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()] ) {
                System.out.println(i);
                int index = stack.pop();
                nums[index] = i - stack.peek() - 1;
            }
            stack.push(i);
        }
        while (stack.peek() != -1 ) {
            int index = stack.pop();
            nums[index] = heights.length - stack.peek() - 1;
        }

        int max = 0;
        for (int i = 0; i<heights.length;i++) {
            System.out.println(nums[i]);
            max = Math.max(max, heights[i]*(nums[i]));
        }
        return max;
    }

    // another way to avoid using the stack but faster is to use two array to record the Left
}
