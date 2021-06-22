package BinaryTree;

import com.sun.source.tree.Tree;

import java.util.*;

public class Binary_Tree_Zigzag_Level_Order_Traversal103 {

    // My idea is to use the iterative BFS method;
    // include the level information in the queue;
    // if we in the odd level, we need to add the result in the addfirst each time;
    // use the
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Boolean> levels = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();

        queue.offer(root);
        levels.offer(true);
        LinkedList<Integer> temp = new LinkedList<>();
        boolean level = true;
        while (!queue.isEmpty()) {
            // get the current node first;
            TreeNode curr = queue.poll();
            boolean currLevel = levels.poll();
            if (currLevel != level) {
                res.add(temp);
                temp = new LinkedList<>();
                level = currLevel;
            }
            if (curr == null) continue;
            if (level) {
                temp.add(curr.val);
            } else {
                temp.addFirst(curr.val);
            }
            // add the left and right node;
            queue.offer(curr.left);
            levels.offer(!level);
            queue.offer(curr.right);
            levels.offer(!level);
        }
        return res;
    }

// Another way to separate the levels is to add the null at each time we scan one level;
//      LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
//      LinkedList<Integer> level_list = new LinkedList<Integer>();
//      node_queue.addLast(root);
//      node_queue.addLast(null);
//     // we finish the scan of one level
//      results.add(level_list);
//      level_list = new LinkedList<Integer>();
//    // prepare for the next level
//       if (node_queue.size() > 0)
//          node_queue.addLast(null);
}
