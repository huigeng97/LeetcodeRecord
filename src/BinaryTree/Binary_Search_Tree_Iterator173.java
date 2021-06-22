package BinaryTree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Binary_Search_Tree_Iterator173 {
    class BSTIterator {

        // create a pointer to the smallest node;
        // each time we call the next;
        // we follow the inorder to iterative the nodes
        // hasNext is to see if the pointer is pointer to some nodes with val;

        /// Very good question in choosing the iterative or the recursive ???
        // To satisfy the O(1) amortized time and O(height) space; we have to use the Iterative;

        // However, the reason we had to resort to such an approach was because we can control the iteration over the array.
        // We can't really pause a recursion in between and then start it off sometime later.

        Deque<TreeNode> stack = new ArrayDeque<>();
        private void addleftInorder(TreeNode root) {
            // For a given node, add all the elements in the leftmost branch of the tree
            // under it to the stack.
            while (root != null) {
                this.stack.push(root);
                root = root.left;
            }
        }

        public BSTIterator(TreeNode root) {
            TreeNode curr = root;
            addleftInorder(root);
        }

        public int next() {

            if (hasNext()) {
                TreeNode curr = stack.pop();
                int value = curr.val;
                curr = curr.right;
                addleftInorder(curr);
                return value;
            }
            return -1;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
