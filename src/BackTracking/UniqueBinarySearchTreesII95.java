package BackTracking;

import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII95 {

    public List<TreeNode> generateTrees(int n) {

        return Helper(1, n);
    }


    // 论如何将Code写的优雅！
//    如果start>end，需要返回的是一个包含NULL的vector<TreeNode*>，而不是空数组。否则之后的二重循环就可能无法展开。
    // 否则就会变成下面的shit !!!


    private List<TreeNode> Helper(int start, int end) {
        List<TreeNode> curr = new ArrayList<>();
        if (start > end) {

//            curr.add(null);
//            return curr;

            return null;
        }
        if (start == end) {
            curr.add(new TreeNode(start));
            return curr;
        }
        for (TreeNode right : Helper(start + 1, end)) {
            TreeNode root = new TreeNode(start);
            root.right = right;
            curr.add(root);
        }
        for (TreeNode left : Helper(start, end - 1)) {
            TreeNode root = new TreeNode(end);
            root.left = left;
            curr.add(root);
        }
        for (int i = start + 1; i <= end - 1; i++) {
            for (TreeNode left : Helper(start, i - 1)) {
                for (TreeNode right : Helper(i + 1, end)) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    curr.add(root);
                }
            }
        }
        return curr;
    }


}
