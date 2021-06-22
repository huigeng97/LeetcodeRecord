package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class invert_binary_tree226 {
/**
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 **/

// This is a  Recursion method
// Time complexity o(n), memory cpt o(n);
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        else {
            TreeNode temp = root.left;
            root.left = invertTree(root.right);
            root.right = invertTree(temp);
        }
        return root;
    }

    // Try an iterative method
    // inorder traverse and then invert each of the node;
    // Initialize the queue with the LinkedList;
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> pq = new LinkedList<>();
        pq.add(root);
        while (!pq.isEmpty()) {
            TreeNode curr = pq.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null) pq.offer(curr.left);
            if (curr.right != null) pq.offer(curr.right);
        }
        return  root;
    }
}
