package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MinStack155 {

    // This is what the interviewer wants!!!
    // write entire structure by yourself!
    class MinStack2 {

        /** initialize your data structure here. */
        public class Node {
            int val;
            int min;
            Node next;

            public Node(int val, int min) {
                this.val = val;
                this.min = min;
            }

            public Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }

        private Node head;

        public MinStack2() {
        }

        public void push(int val) {
            if (head == null) head = new Node(val,val);
            else {
                head = new Node(val, Math.min(head.min,val), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }


    class MinStack {

        public class Node {
            int val;
            int min;
            Node next;

            public Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
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
