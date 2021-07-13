package DP2;

import BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII337 {

    // TLE solution, not memo;

    public int rob(TreeNode root) {
        return Helper(root,false);
    }

    // Taken shows if the parents of the node is taken;
    private int Helper(TreeNode root, boolean taken) {
        if (root == null) {return 0;}
        int notTaken = Helper(root.left,false) + Helper(root.right, false);
        if (taken) {
            return notTaken;
        } else {
            return Math.max(notTaken, Helper(root.left,true) + Helper(root.right,true) + root.val);
        }
    }


    public int rob2(TreeNode root) {

        return Helper2(root,false);
    }

    Map<TreeNode, Integer> mapTrue = new HashMap<>();
    Map<TreeNode, Integer> mapFalse = new HashMap<>();

    // Taken shows if the parents of the node is taken;
    private int Helper2(TreeNode root, boolean taken) {
        if (root == null) {return 0;}
        if (taken) {
            if (mapTrue.containsKey(root)) {
                return mapTrue.get(root);
            }
        } else {
            if (mapFalse.containsKey(root)) {
                return mapFalse.get(root);
            }
        }
        int notTaken = Helper(root.left,false) + Helper(root.right, false);
        if (taken) {
            mapTrue.put(root, notTaken);
            return notTaken;
        } else {
            int isTaken = Helper(root.left,true) + Helper(root.right,true);
            return Math.max(notTaken, isTaken + root.val);
        }
    }


    // This solution is more elegant than mine; (probably most elegant one)
    // Use the two element array to avoid the duplicate calculation;

    public int[] helper(TreeNode node) {
        // return [rob this node, not rob this node]
        if (node == null) {
            return new int[] { 0, 0 };
        }
        int[] left = helper(node.left);
        int[] right = helper(node.right);
        // if we rob this node, we cannot rob its children
        int rob = node.val + left[1] + right[1];
        // else, we free to choose rob its children or not
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] { rob, notRob };
    }

    public int rob3(TreeNode root) {
        int[] answer = helper(root);
        return Math.max(answer[0], answer[1]);
    }
}
