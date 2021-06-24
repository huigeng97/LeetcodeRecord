package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;


public class OnlineStockSpan901 {

    class StockSpanner {
        Stack<Integer[]> stack;
        public StockSpanner() {
            stack = new Stack<Integer[]>();
        }

        public int next(int price) {
            int num = 1;
            while (!stack.isEmpty() && price >= stack.peek()[0]) {
                num += stack.pop()[1];

            }
            stack.push(new Integer[]{price,num});
            return num;
        }
    }

    /// The fastest way is to write the stack by yourself....
    class StockSpanner2 {
        Node head;
        public StockSpanner2() {
        }

        public class Node {
            int[] val;
            Node next;

            public Node(int val, int min) {
                this.val = new int[]{val,min};
            }

            public Node(int val, int min, Node next) {
                this.val = new int[]{val,min};
                this.next = next;
            }

            public int[] pop() {
                int[] temp = head.val;
                head = head.next;
                return temp;
            }

            public int[] peek() {
                return head.val;
            }
        }

        public int next(int price) {
            int num = 1;
            while (head!=null && price >= head.peek()[0]) {
                num += head.pop()[1];
            }
            if (head == null) head = new Node(price,num);
            else {
                head = new Node(price, num, head);
            }
            return num;
        }
    }


}
