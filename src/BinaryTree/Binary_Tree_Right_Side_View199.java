package BinaryTree;

import java.util.*;


public class Binary_Tree_Right_Side_View199 {


    // leave as a review question;

    // other method like two queue / levelcount  are pretty the same idea with the null (sentinel ) method
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> rightside = new ArrayList<>();

        queue.offer(root);
        queue.offer(null);
        int temp = root.val;
        boolean level = true;
        while (!queue.isEmpty()) {
            // get the current node first;
            TreeNode curr = queue.poll();
            if (curr != null) {
                temp = curr.val;
                // add the left and right node;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            } else {
                rightside.add(temp);
                if (!queue.isEmpty())
                    queue.offer(null);
            }
        }
        return rightside;
    }


    // recursive DFS is the faster method in this problem;
}
