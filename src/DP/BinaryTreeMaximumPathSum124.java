package DP;

import BinaryTree.TreeNode;

public class BinaryTreeMaximumPathSum124 {

    int sum = 0;
    public int maxPathSum(TreeNode root) {

        // find the max starting from the root;
        // max = root.val + helper(root.left) + helper(root.right);
        helper(root);
        return sum;
    }

    private int helper(TreeNode root) {
        if (root == null) {return 0;}
        int left = helper(root.left);
        int right = helper(root.right);
        right = right > 0 ? right : 0;
        left = left > 0 ? left : 0;

        int max = root.val + left + right;
        sum = Math.max(max, sum);
        int value = Math.max(left, right);

        return root.val + value;
    }
}
