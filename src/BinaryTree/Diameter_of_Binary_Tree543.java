package BinaryTree;

public class Diameter_of_Binary_Tree543 {
    // Many duplicate operation 14% 17%;






    /// with slightly change of the method; (same idea)
    /// we could achieve faster with each node only calculate once;
    /// the most important things for a recursive method is how to separate the function to avoid the duplicate calculation;
    // Beat 100% 38%;
    private int diameter;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        diameter = 0;
        getLevel(root);
        return diameter;
    }

    private int getLevel(TreeNode root) {
        if (root == null) return 0;

        int left = getLevel(root.left);
        int right = getLevel(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }


}
