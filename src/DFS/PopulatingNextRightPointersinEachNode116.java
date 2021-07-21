package DFS;

import java.util.*;

public class PopulatingNextRightPointersinEachNode116 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {

        // this problem could be done with a layer by layer BFS
        // and store the prev node to connect the curr;

        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        Node prev = null;
        Node curr;
//        while (!queue.isEmpty()) {
//            curr = queue.poll();
//            if (prev != null) {
//                prev.next = curr;
//            }
//            if (curr != null) {
//                if (curr.left == null) {
//                    queue.offer(curr.left);
//                    queue.offer(curr.right);
//                }
//            }
//            else if (curr == null && !queue.isEmpty()) {
//                queue.offer(null);
//            }
//            prev = curr;
//        }

        while (queue.peek() != null) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                curr = queue.poll();
                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;
                if (curr != null && curr.left != null) {
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            }
            queue.offer(null);
        }

        return root;
    }



    // DFS is much easier to code;

    public Node connect2(Node root) {
        dfs(root, null);
        return root;
    }

    private void dfs(Node curr, Node next) {
        if (curr == null) {return;}
        curr.next = next;
        dfs(curr.left, curr.right);
        dfs(curr.right, curr.next == null ? null : curr.next.left);
    }

}
