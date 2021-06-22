package BinaryTree;

import java.util.HashMap;
import java.util.Map;

public class Binary_Tree_Maximum_Path_Sum124 {

    // The max path = max (left max path , right max path, left half max path + right half max path + root val)
    // How to optimize to only calculate once the half max;

    // When we use the recursion, think about which part is the recursive, if we find there is a part of function;
    // calculate many times, it usually means we didn't catch the most important recursion part;
    int max = Integer.MIN_VALUE;
//    Map<TreeNode,Integer> halfmax = new HashMap<>();
    public int maxPathSum(TreeNode root) {
        findhalfmax(root);
        return max;
    }

    private int findhalfmax(TreeNode node) {
        if (node == null) return 0;
        int right = findhalfmax(node.right);
        right = right > 0 ? right : 0;

        // Alternative way is
        // int right = Math.max(findhalfmax(node.right), 0);
        // more concise and beautiful;

        int left = findhalfmax(node.left);
        left = left > 0 ? left : 0;
        max  = Math.max(max, node.val + right + left);
        int value = node.val + Math.max(right , left);
//        halfmax.put(node,value);
        return value;
    }
//    private void findmaxPath(TreeNode root) {
//        if (root == null) return;
//        findmaxPath(root.left);
//        findmaxPath(root.right);
//        int right, left;
//        if (halfmax.containsKey(root.right)) {
//            right = halfmax.get(root.right);
//        } else {
//            right = findhalfmax(root.right);
//        }
//        if (halfmax.containsKey(root.left)) {
//            left = halfmax.get(root.left);
//        } else {
//            left = findhalfmax(root.left);
//        }
//        max = Math.max((right > 0 ? right : 0) + (left > 0 ? left : 0) +root.val,max);
//    }




}
