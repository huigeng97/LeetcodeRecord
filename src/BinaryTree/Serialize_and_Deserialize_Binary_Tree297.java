package BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Serialize_and_Deserialize_Binary_Tree297 {

    public class Codec {

        // The idea is to build a complete binary search tree CBST, and then convert it in to the string;
        // we could use an integer arraylist to store the CBST;
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "null";
            return root.val + "," +  serialize(root.left) + "," + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nodes = data.split(",");
            List<String> data_list = new LinkedList<String>(Arrays.asList(nodes));
            return rdeserialize(data_list);

//            if (nodes[0].equals("")) return null;
//            TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
//            TreeNode prev = root;
//            for (int i = 1; i < nodes.length; i++) {
//                if (!nodes[i].equals("")) {
//                    root.left = new TreeNode(Integer.valueOf(nodes[i]));
//                    prev = root;
//                    root = root.left;
//                }
//            }
        }
    }

    private TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

}
