package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MinStack155 {
    class MinStack {

        /** initialize your data structure here. */

        Deque<Integer> stack;
        PriorityQueue<Integer> pq;
        public MinStack() {
            stack = new ArrayDeque<>();
            pq = new PriorityQueue<>();
        }

        public void push(int val) {
            stack.push(val);
            pq.offer(val);
        }

        public void pop() {
            Integer a = stack.pop();
            pq.remove(a);
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return pq.peek();
        }
    }


    /// Other implement like memorizing the min value with curr value as a pairs ;
    // Use two stacks to memorize the minstack and stack;
}
