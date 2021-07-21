package BinaryTree;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorofaBinaryTree236 {
    TreeNode res;
    boolean seen;
    // This problem should be use the recursive call on the Postorder traversal;
    // if we traverse a node contains the two nodes, we return it;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findLCA(root,p,q,0);
        return res;
    }

    private int findLCA(TreeNode node, TreeNode p, TreeNode q, int count) {
        if (node == null || seen) return 0;
        // left first , right second,
        count += findLCA(node.left,p,q,0);
        count += findLCA(node.right,p,q,0);
        if (node == p) count++;
        if (node == q) count++;

        if (count == 2 && !seen) {
            res = node;
            seen = true;
        }
        return count;
    }


    // This is for the Lowest_Common_Ancestor_of_a_Binary_Treeiv 1676
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode[] nodes) {
        Set<Integer> nodeset = new HashSet<>();
        for (int i = 0; i < nodes.length; i++) {
            nodeset.add(nodes[i].val);
        }
        findLCA(root,nodeset,0);
        return res;
    }

    boolean seen2 = false;
    private int findLCA(TreeNode node, Set<Integer> nodeset, int count) {
        if (node == null || seen2) return 0;
        count += findLCA(node.left,nodeset,0);
        count += findLCA(node.right,nodeset,0);
        if (nodeset.contains(node.val)) count++;
        if (count == nodeset.size() && res == null) {
            res = node;
            seen2 = true;
        }
        return count;
    }


}
