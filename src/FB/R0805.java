package FB;

import BinaryTree.TreeNode;

public class R0805 {
    /*
    One step right and then always left
    */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    /*
    One step left and then always right
    */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return null;

        // delete from the right subtree
        if (key > root.val) root.right = deleteNode(root.right, key);
            // delete from the left subtree
        else if (key < root.val) root.left = deleteNode(root.left, key);
            // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null) root = null;
                // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // find the root;
            if (root.left == null) {
                return root.right;
            } else {
                TreeNode replace = root.left;
                // replace the root val with the largest of the left tree
                while (replace.right != null) {
                    replace = replace.right;
                }
                root.val = replace.val;
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    // LeetCode 236

    boolean seen = false;
    TreeNode res;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        res = root;
        helper(root, p, q);
        return res;
    }

    private int helper(TreeNode root, TreeNode p, TreeNode q) {
        if (seen || root == null) {
            return 0;
        }
        int sum = 0;
        if (root == q || root == p) {
            sum++;
        }
        sum += helper(root.left, p, q) + helper(root.right, p, q);
        if (sum == 2 && !seen) {
            res = root;
            seen = true;
        }
        return sum;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null || root==p || root==q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left!= null && right!= null)
            return root;
        else if (left == null && right != null)
            return right;
        else if (left != null && right == null)
            return left;
        else
            return null;
    }
}
