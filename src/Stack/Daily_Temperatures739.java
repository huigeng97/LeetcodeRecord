package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Daily_Temperatures739 {

    // 这道题感觉做过，larger number on the right side;
    // 可以用 bucket sort; segment dividing algorithm
    //
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new LinkedList<>();
        // This problem is to find the next large number;
        // we could use the stack to store the index, and if a item is pop, we update the res with the index differece;
        int[] res= new int[temperatures.length];
        // since we update the value when we pop the items (a number larger than this one), we should use the decreasing stack;,
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }

    // We actually could use an array and pointer to store the stack;
    // pretty the same thing, but much faster;
    public int[] dailyTemperatures2(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int size = 0;
        // This problem is to find the next large number;
        // we could use the stack to store the index, and if a item is pop, we update the res with the index differece;
        int[] res= new int[temperatures.length];
        // since we update the value when we pop the items (a number larger than this one), we should use the decreasing stack;,
        for (int i = 0; i < temperatures.length; i++) {
            while (size != 0 && temperatures[i] > temperatures[stack[size-1]]) {
                int index = stack[--size];
                res[index] = i - index;
            }
            stack[size++] = i;
        }
        return res;
    }



    public static void main(String[] args) {

        Daily_Temperatures739 test = new Daily_Temperatures739();
        test.dailyTemperatures(new int[]{30,60,30,90,100});

    }
}
