package A.DailyPractice;

import BinaryTree.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Test426 {

    public Node treeToDoublyList(Node root) {

        if (root == null) { return null;}
        Node head = new Node();
        Node dummy = head;

        // InOrder traversal using Stack (BFS) // iterative method;
        Node curr = root;
        Deque<Node> stack = new ArrayDeque<>();
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        Node prev = head;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            prev.right = curr;
            curr.left = prev;
            prev = curr;
            if (curr.right != null) {
                curr = curr.right;
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            }
        }
        curr.right = head.right;
        curr.right.left = curr;
        return head.right;
    }
}
