package weeklyContest;

import BinaryTree.TreeNode;

import java.util.*;

public class Contest {
    public int[] getConcatenation(int[] nums) {
        int len = 0;
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = nums[i];
            res[i + len] = nums[i];
        }
        return res;
    }

    public int countPalindromicSubsequence(String s) {
        if (s == null || s.length() <= 2) {
            return 0;
        }
        int len = s.length();
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < len - 2; i++) {
            if (set.contains(s.charAt(i))) {
                continue;
            }
            res += check(s, s.charAt(i), i + 1);
        }
        return res;
    }

    private int check(String s, char ch, int i) {
        int len = s.length();
        int res = 0;
        for (int j = len - 1; j >= i; j--) {
            if (s.charAt(j) == ch) {
                Set<Character> set = new HashSet<>();
                int curr = j - 1;
                while (curr >= i) {
                    if (!set.contains(s.charAt(curr))) {
                        res++;
                        set.add(s.charAt(curr));
                    }
                }
                return res;
            }
        }
        return res;
    }


    // f11 = 3; f12 = 6; f13 = 12 = f12 * 3; f1n = 2 * f1(n-1) = 3 * 2 ^ n -1;
    // f21 = 6; f22 = 0120, 0110,0112,0220,0221,0210 = 6; = f12 * 3 = 18;
    // f55 = 3 * 2 * 2 * 2 * 2 * 3 * 3 * 3 * 3
    public int colorTheGrid(int m, int n) {

        if (m == 0 || n == 0) { return 0; }
        return 0;
    }

  public static void main(String[] args) {
    //
      Contest test = new Contest();
      test.colorTheGrid(5,5);
  }

  // All the root in the list of leaf except one;

    Map<Integer, Integer[]> leafMap = new HashMap<>();
    public TreeNode canMerge(List<TreeNode> trees) {

        Map<Integer, Integer[]> rootMap = new HashMap<>();
        for (TreeNode root : trees) {
            rootMap.put(root.val, getMinMax(root));
        }
        return new TreeNode();
    }

    private Integer[] getMinMax(TreeNode root) {
        return null;
    }
}
