package BinaryTree;

import java.util.*;

public class Validate_Binary_Search_Tree98 {



    /// This is a good question to review recursion and iteration
    // also BFS (level order) DFS ( Postorder, Preorder, Inorder) ;
    // For this problem, we could use the Recursive / Iterative combine with BFS (level order) / DFS (inorder);

    // Recursive BFS
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBST(root.left, null , root.val) && isValidBST(root.right, root.val,null);
    }

    private boolean isValidBST(TreeNode root, Integer low, Integer high) {
        if (root == null) return true;
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        return isValidBST(root.left, low, root.val) && isValidBST(root.right,root.val,high);
    }

    // Iterative BFS
    // Beat 7% 16.5%;
    // BFS iterative应该是个Queue
    private Queue<TreeNode> queue = new LinkedList<>();
    private Queue<Integer> upper = new LinkedList<>();
    private Queue<Integer> lower = new LinkedList<>();

    public void update(TreeNode root, Integer low, Integer high) {
        queue.add(root);
        upper.add(high);
        lower.add(low);
    }

    public boolean isValidBST2(TreeNode root) {
        Integer low = null;
        Integer high = null;
        update(root,low,high);

        while (!queue.isEmpty()) {
            // get the node first;
            root = queue.poll();
            low = lower.poll();
            high = upper.poll();
            if (root == null) continue;
            int val = root.val;
            if ((low != null && val <= low) || (high != null && val >= high)) {
                return false;
            }
            // add the left and then right;
            update(root.left, low, val);
            update(root.right, val, high);
        }
        return true;
    }


    // DFS inorder recursive;
    // Inorder traverse the Tree, check if the next element is larger than prev element;
    // inorder is : left node right;
    // Beat 100% 22%
    private Integer prev;

    public boolean isValidBST3(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) return true;
        if (!inorder(root.left)) {
            return false;
        }
        if (prev != null && root.val <= prev) return false;
        prev = root.val;
        return inorder(root.right);
    }

    // The last one is the iterative DFS inorder;
    // Iterative DFS use the stack to achieve it;
    // In java we use the Deque to achieve the stack function (fast, but don't accept the null);

    public boolean isValidBST4(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer pres = null;

        while (!stack.isEmpty()  || root != null ) {
            // add the left first;
            // two while loops;
            while (root!= null) {
                stack.add(root);
                root = root.left;
            }
            // get the node second;
            root = stack.pop();
            if (pres != null  && root.val <= prev) {
                return false;
            }
            prev = root.val;
            // add the right next;
            root = root.right;
        }
        return true;

    }



}
