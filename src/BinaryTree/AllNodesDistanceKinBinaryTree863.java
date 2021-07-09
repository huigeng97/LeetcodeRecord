package BinaryTree;

import java.util.*;

/**
 * @author Geng Hui
 */
public class AllNodesDistanceKinBinaryTree863 {
    Map<TreeNode, TreeNode> map = new HashMap<>();
    HashSet<TreeNode> seen = new HashSet<>();
    List<Integer> nodes = new ArrayList<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // use the dfs to assign all the node with the parents;
        dfs(root, null);
        bfsHelper(target, k);
        // use the bfs to find the all the nodes with the distance of K;
        return nodes;
    }

    private void bfsHelper(TreeNode target, int k) {
        if (target == null || seen.contains(target)) {
            return;
        }
        if (k == 0) {
            nodes.add(target.val);
            return;
        }
        seen.add(target);
        bfsHelper(target.left,k - 1);
        bfsHelper(target.right,k - 1);
        bfsHelper(map.get(target),k - 1);
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node == null) {return;}
        map.put(node, par);
        dfs(node.left,node);
        dfs(node.right,node);
    }


    // another interesting solution is to only annotate the target parents distance from target to roots
    // then all the others nodes could be calculated the distance with the BFS;

}

