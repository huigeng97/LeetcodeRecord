package DFS;

import BinaryTree.Node;
import BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBinaryTree297 {

    public String serialize(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        String res = "";
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                res += "#" + ",";
            } else {
                res += curr.val + ",";
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        return res;
    }

    // Decodes your encoded data to tree.
    // the data is a complete BT which all the leaves are null;

    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        if (nodes[0].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeft = true;
        for (int i = 0; i < nodes.length - 1; i++) {
            TreeNode curr = queue.poll();
            if (nodes[i].equals("#")) {
                curr = null;
            } else {
                curr.val = Integer.valueOf(nodes[i]);
                TreeNode left = new TreeNode(-1001);
                TreeNode right = new TreeNode(-1001);
                curr.left = left;
                curr.right = right;
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }

        clean(root);
        return root;
    }

    private void clean(TreeNode root) {
        if (root == null) {return;};
        if (root.left.val == -1001) {
            root.left = null;
        } else {
            clean(root.left);
        }
        if (root.right.val == -1001) {
            root.right = null;
        } else {
            clean(root.right);
        }
    }

    // it could be better to deserialize;
    // This is a DFS deserialization;
    public String serialize2(TreeNode root) {
        if (root == null) {
            return "#" + ",";
        }
        return root.val + "," + serialize(root.left) + serialize(root.right);
    }

    int start = 0;
    public TreeNode deserialize2(String data) {
        String[] nodes = data.split(",");
        return Helper(nodes);
    }

    private TreeNode Helper(String[] nodes) {
        if (nodes[start].equals("#")) {
            start++;
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.valueOf(Integer.valueOf(nodes[start])));
            start ++;
            root.left = Helper(nodes);
            root.right = Helper(nodes);
            return root;
        }
    }
}
