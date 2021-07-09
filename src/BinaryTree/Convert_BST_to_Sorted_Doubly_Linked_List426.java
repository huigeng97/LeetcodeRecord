package BinaryTree;

import java.util.Stack;

public class Convert_BST_to_Sorted_Doubly_Linked_List426 {
    public Node treeToDoublyList(Node root) {

        Node head = root;
        Stack<Node> stack = new Stack<>();
        while (head.left != null) {
            stack.push(head);
            head = head.left;
        }
        Node node = new Node(0);
        stack.push(head);
        while (!stack.isEmpty()) {
            // 先pop, curr 是目前最小的值；
            // 先连上node, 然后再看右边有没有人；
            Node curr = stack.pop();
            curr.left = node;
            node.right = curr;
            node = curr;
            if (curr.right != null) {
                curr = curr.right;
                while (curr.left != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            }

        }
        node.right = head;
        head.left = node;
        return head;
    }


    /// another method is to use a recursive helper method to call on each node with the InOrder;

    Node first = null;
    Node last = null;

    public void helper(Node node) {
        if (node != null) {
            // left
            helper(node.left);
            // node
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            }
            else {
                // only the first recursive function will go into this part;
                // which means we find the smallest one;
                // keep the smallest node
                // to close DLL later on
                first = node;
            }
            last = node;
            // right
            helper(node.right);
        }
    }

    public Node treeToDoublyList2(Node root) {
        if (root == null) {return null;}

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }

}
