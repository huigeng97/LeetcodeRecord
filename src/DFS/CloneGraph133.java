package DFS;

import java.util.*;

public class CloneGraph133 {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        HashMap<Integer, Node> map = new HashMap<>();
        HashSet<Integer> seen = new HashSet<>();

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(node);
        seen.add(node.val);
        Node copy = new Node(node.val);
        map.put(node.val, copy);
        Node temp = copy;
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (map.containsKey(curr.val)) {
                temp = map.get(curr.val);
            } else {
                temp = new Node(curr.val);
                map.put(curr.val, temp);
            }
            for (Node nei : curr.neighbors) {
                Node neiCope;
                if (!seen.contains(nei.val)) {
                    stack.push(nei);
                    seen.add(nei.val);
                }
                if (map.containsKey(nei.val)) {
                    neiCope = map.get(nei.val);
                } else {
                    neiCope = new Node(nei.val);
                    map.put(nei.val, neiCope);
                }
                temp.neighbors.add(neiCope);
            }
        }
        return copy;
    }


    // Recursive is easier and straightforward;

    HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph2(Node node) {

        if (node == null) {
            return null;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node copy = new Node(node.val);
        map.put(node , copy);

        for (Node nei : node.neighbors) {
            Node temp = cloneGraph(nei);
            copy.neighbors.add(temp);
        }

        return copy;
    }
}
