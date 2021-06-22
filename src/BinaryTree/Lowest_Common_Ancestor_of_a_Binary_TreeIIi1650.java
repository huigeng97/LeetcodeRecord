package BinaryTree;

import java.util.HashSet;
import java.util.Set;

// This is a very tricky question!!!! Actually it is the intersection of two linkedList!!!!

public class Lowest_Common_Ancestor_of_a_Binary_TreeIIi1650 {
    public class Node {
        public int val;
        public Node parent;
        public BinaryTree.Node left;
        public BinaryTree.Node right;

        public Node(int val, BinaryTree.Node left, BinaryTree.Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(){}

        public Node(int val) {
            this.val = val;
        }
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> nodes = new HashSet<>();
        while (p!=null) {
            nodes.add(p);
            p = p.parent;
        }
        while (q!=null) {
            if (nodes.contains(q)) {
                return q;
            }
            q = q.parent;
        }
        throw  new IllegalArgumentException();
    }


    //
    public Node lowestCommonAncestor2(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a == null? q : a.parent;
            b = b == null? p : b.parent;
        }
        return a;
    }
}
