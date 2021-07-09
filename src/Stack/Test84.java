package Stack;

import java.util.Stack;

public class Test84 {


    // 抓住核心： stack 顺序为，上一个元素是第一个比下一个小的元素，每个元素被第一个比自己小的元素pop;
    //


    public int largestRectangleArea(int[] heights) {

        // do increasing stack
        // when one item is pop, the area of it is the curr idx - its idx * its heights;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek()!= -1 && heights[i] <= heights[stack.peek()]) {
                int idx = stack.pop();
                max = Math.max(max, (i - stack.peek() - 1) * heights[idx]);
            }
            stack.push(i);
        }

        // calculate the remaining item;
        while (stack.peek()!= -1) {
            int idx = stack.pop();
            max = Math.max(max, (heights.length - stack.peek() - 1) * heights[idx]);
        }
        return max;
    }

}
