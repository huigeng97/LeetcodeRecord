package DFS;

import BinaryTree.TreeNode;

public class CountGoodNodesinBinaryTree1448 {

    public int goodNodes(TreeNode root) {

        return helper(root,Integer.MIN_VALUE);
    }

    private int helper(TreeNode root, int maxValue) {
        int res = 0;
        if (root == null) {
            return res;
        }

        if (root.val >= maxValue) {
            res += 1;
            maxValue = root.val;
        }

        res += helper(root.right, maxValue) + helper(root.left, maxValue);

        return res;
    }
}
