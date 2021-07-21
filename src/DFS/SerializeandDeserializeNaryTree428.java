package DFS;

import BinaryTree.TreeNode;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class SerializeandDeserializeNaryTree428 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // try dfs first
    public String serialize(Node root) {
        if (root == null) {
            return "#";
        }
        String res = Integer.toString(root.val);
        if (root.children.size() == 0) {
            res += "," + "#";
        } else {
            for (Node child : root.children) {
                res += "," + serialize(child);
            }
            res += "," + "#";
        }
        return res;
    }

    // Decodes your encoded data to tree.
    int start = 0;
    public Node deserialize(String data) {
        String[] nodes = data.split(",");
        return Helper(nodes);
    }

    private Node Helper(String[] nodes) {
        if (nodes[start].equals("#")) {
            start++;
            return null;
        } else {
            Node root = new Node(Integer.valueOf(nodes[start]));
            root.children = new ArrayList();
            start++;
            Node curr = Helper(nodes);
            while (curr != null && start < nodes.length) {
                root.children.add(curr);
                curr = Helper(nodes);
            }
            return root;
        }
    }
}
