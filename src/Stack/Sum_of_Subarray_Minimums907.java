package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Sum_of_Subarray_Minimums907 {

    // This problem is to find the number of the left strict larger element and right strick larger elements;
    // use the increasing stack and count++ each time
    public int sumSubarrayMins(int[] arr) {
        // store the prev large element;
        int[] left = new int[arr.length];
        // store the next large element;
        int[] right = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0 ; i < arr.length ; i ++) {
            int num = 1;
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int index = stack.pop();
                num += left[index];
            }
            stack.push(i);
            left[i] = num;
        }

        stack = new ArrayDeque<>();

        for (int i = arr.length-1 ; i >= 0 ; i --) {
            int num = 1;
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int index = stack.pop();
                num += right[index];
            }
            stack.push(i);
            right[i] = num;
        }

        long res = 0, mod = (int)1e9 + 7;
        for (int i = 0 ; i < arr.length ; i ++) {
            res = (res + (long)arr[i] * left[i] * right[i]) % mod;
        }

        return (int) res;
    }
}
