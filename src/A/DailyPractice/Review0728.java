package A.DailyPractice;

import BinaryTree.TreeNode;

import java.util.*;
import java.util.function.BiFunction;

public class Review0728 {

    // This is the base code, which is right;
    // But we need to add someth ing to make it faster (terminate earlier !)

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 1) {
            return s1.equals(s2);
        }

        for (int i = 1; i < s1.length(); i++) {
            // if faster screening works:
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i, s1.length()), s2.substring(i,s2.length()))) {
                return true;
            } else if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i, s2.length()))
                    && isScramble(s1.substring(i, s1.length()), s2.substring(0, s2.length() - i))) {
                return true;
            }

        }
        return false;
    }

    public boolean isPalindrome(String s) {
        s.toUpperCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (s.charAt(right) == s.charAt(left)) {
                right--;
                left++;
            } else {
                return false;
            }
        }
        return true;
    }

    public int minDepth(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        int level = 0;
        while (queue.peek() != null) {
            level += 1;
            while (queue.peek() != null) {
                TreeNode curr = queue.poll();

                if (curr.left == null && curr.right == null) {
                    return level;
                } else {
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                }

            }
            queue.poll();
            queue.offer(null);
        }
        return level;
    }

    //
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (queue.peek() != null) {
            List<Integer> temp = new ArrayList<>();
            while (queue.peek() != null) {
                TreeNode curr = queue.poll();
                temp.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            queue.poll();
            queue.offer(null);
            res.addFirst(temp);
        }
        return res;
    }

    int count = 0;
    public int numDistinct2(String s, String t) {
        if (t.length() > s.length()) {
            return 0;
        }
        helper(s,t,0,0);
        return count;
    }

    private void helper(String s, String t, int sIdx, int tIdx) {
        if (tIdx == t.length()) {
            count++;
            return;
        }
        if (sIdx == s.length()) {
            return;
        }

        if (s.charAt(sIdx) == t.charAt(tIdx)) {
            helper(s,t,sIdx + 1, tIdx+1);
        }
        helper(s,t,sIdx + 1, tIdx);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (queue.peek() != null) {
            int levelNum = queue.size();

            for(int i=0; i<levelNum - 1; i++) {
                Node curr = queue.poll();
                assert curr != null;
                curr.next = queue.peek();
                // TODO: something per node;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            // TODO: something per level
            queue.poll();
            queue.offer(null);
        }
        return root;
    }

    public int numDistinct(String s, String t) {

        int[][] dp = new int[t.length() + 1][s.length() + 1];
        Arrays.fill(dp[0],1);
        // dp[i][j] record the total variable for t.substring(0,j) until s.substring(0,i);
        // transition function:
        // if (s.charAt(i) == t.charAt(j)) : s[i][j] = s[i-1][j-1] + s[i-1][j];
        // else s[i][j] = s[i-1][j];
        for (int i = 0 ; i < t.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                dp[i + 1][j + 1] = dp[i + 1][j];
                if (s.charAt(j) == t.charAt(i)) {
                    dp[i + 1][j + 1] += dp[i][j];
                }
            }
        }
        return dp[t.length()][s.length()];
    }

    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isBalanced(root.left) && isBalanced(root.right)) {
            return  Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1;
        } else {
            return false;
        }
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.right), getHeight(node.left)) + 1;
    }


    // 其实就是在bfs的时候用 return -1; 加了个全局的 return false / true 的功能；
    private int dfsHeight (TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = dfsHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = dfsHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max (leftHeight, rightHeight) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }




    public int minCut(String s) {
        int n = s.length();
        int[] cut = new int[n +1];  // number of cuts for the first k characters
        for (int i = 0; i <= n; i++) {
            cut[i] = i-1;
        }
        for (int i = 0; i < n; i++) {
            // odd length palindrome
            for (int j = 0; i >= j && i+j < n && s.charAt(i-j)==s.charAt(i+j) ; j++) {
                cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j]);
            }
            // even length palindrome
            for (int j = 1; i + 1 >= j && i+j < n && s.charAt(i-j+1)==s.charAt(i+j); j++) {
                cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j+1]);
            }
        }
        return cut[n];
    }


    List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        helper(root);
        return res;
    }

    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        helper(root.left);
        helper(root.right);
        return;
    }

    public List<Integer> postorderTraversal(TreeNode root) {

        LinkedList<Integer> res = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.right == null) {
                res.addFirst(curr.val);
                curr = curr.left;
            }
            else {
                TreeNode prev = curr.right;
                while (prev.left != null && prev.left != curr) {
                    prev = prev.left;
                }
                if (prev.left == null) { // 建立prev对curr的right指针
                    prev.left = curr;
                    // 先do something on the root, then右子树；
                    res.addFirst(curr.val);
                    curr = curr.right;
                }
                else {  // prev.right == curr ， 删除prev对curr的right指针
                    prev.left = null;
                    // 回到root后直接进入左子树
                    curr = curr.left;
                }
            }
        }
        return res;
    }

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (isNumeric(s)) {
                stack.push(Integer.valueOf(s));
            } else {
                Integer second = stack.pop();
                Integer first = stack.pop();
                if (s.equals("+")) {
                    stack.push(first + second);
                } else if (s.equals("-")) {
                    stack.push(first - second);
                } else if (s.equals("*")) {
                    stack.push(first * second);
                } else if (s.equals("/")) {
                    stack.push(first / second);
                }
            }
        }
        return stack.peek();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public int evalRPN2(String[] tokens) {
        Map<String, BiFunction<Integer, Integer, Integer>> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        map.put("+", (Integer i, Integer b) -> { return i + b; } );
        map.put("-", (Integer i, Integer b) -> { return i - b; } );
        map.put("*", (Integer i, Integer b) -> { return i * b; } );
        map.put("/", (Integer i, Integer b) -> { return i / b; } );
        for (String s : tokens) {
            if (!map.containsKey(s)) {
                stack.push(Integer.valueOf(s));
            } else {
                Integer op1 = stack.pop();
                stack.pop();
                Integer op2 = stack.pop();
                stack.pop();
                stack.push(map.get(s).apply(op2, op1));
            }
        }
        return stack.pop();
    }

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.equals("")) {
                sb.append(word);
                sb.append(" ");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.reverse();
        return  new String(sb);
    }

    public int maximumGap(int[] nums)     {
        int N=nums.length;
        if (N<2) {
            return 0;
        }

        int MIN=Integer.MAX_VALUE;
        int MAX=Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++)
        {
            MIN=Math.min(MIN,nums[i]);
            MAX=Math.max(MAX,nums[i]);
        }

        int bucket_size = Math.max(1,(MAX-MIN)/(N-1));
        int bucket_nums = (MAX-MIN)/bucket_size+1;
        int[] bucket_min = new int[bucket_nums];
        Arrays.fill(bucket_min, Integer.MAX_VALUE);
        int[] bucket_max = new int[bucket_nums];
        Arrays.fill(bucket_max, Integer.MIN_VALUE);

        Set<Integer> set = new HashSet<>();
        for (int i=0; i<N; i++)
        {
            int idx=(nums[i]-MIN)/bucket_size;
            bucket_min[idx] = Math.min(nums[i],bucket_min[idx]);
            bucket_max[idx] = Math.max(nums[i],bucket_max[idx]);
            set.add(idx);
        }

        int result=Integer.MIN_VALUE;
        int pre=0;
        for (int i=0; i<bucket_nums; i++)
        {
            if (!set.contains(i)) continue;
            result=Math.max(result,bucket_min[i]-bucket_max[pre]);
            pre = i;
        }

        return result;
    }


    public static void main(String[] args) {
    //
      Review0728 test = new Review0728();System.out.println(test.numDistinct("rabbbit","rabbit"));
  }
}
