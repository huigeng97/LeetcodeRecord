package FB;

import BinaryTree.TreeNode;
import com.sun.source.tree.Tree;
import javafx.util.Pair;

import java.util.*;

public class R0802 {

    // 621 Task Scheduler

    public int leastInterval2(char[] tasks, int n) {

        // first to find the most occurrance char (more than length of tasks / (n-1));
        // if doesn't have such items, return length of tasks;
        // if exist, return (nums of most char - 1) * (n + 1) + 1;
        int[] counts = new int[26];
        for (char ch : tasks) {
            counts[ch - 'A']++;
        }

        int max = 0;
        for (int count : counts) {
            max = Math.max(count, max);
        }

        int maxCount = 0;
        for (int count : counts) {
            if (count == max) {
                maxCount++;
            }
        }

        return Math.max(tasks.length, (max - 1) * (n + 1) + maxCount);

    }

    class pair {
        int freq;
        char ch;

        public pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }
    public String rearrangeString(String s, int k) {

        if(s == null || s.isEmpty() || k ==0) {
            return s;
        }
        if(k > s.length()) {
            return "";
        }

        PriorityQueue<pair> pq = new PriorityQueue<>((a,b) -> (b.freq == a.freq ? a.ch - b.ch : b.freq - a.freq));
        Map<Character, Integer> map = new HashMap();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(new pair(entry.getValue(), entry.getKey()));
        }

        StringBuilder sb = new StringBuilder();
        // put the item into the res;
        while (!pq.isEmpty()){
            if(pq.size() >= k){
                List<pair> list = new ArrayList();
                for (int i=1 ; i <= k; i++){
                    pair node = pq.poll();
                    list.add(node);
                    sb.append(node.ch);
                }
                for (pair node: list){
                    if(node.freq -1 > 0){
                        pq.offer(new pair(node.freq-1,node.ch));
                    }
                }
            } else {
                while (!pq.isEmpty()){
                    pair node = pq.poll();
                    if(node.freq > 1) {
                        return "";
                    }
                    sb.append(node.ch);
                }
            }
        }
        return sb.toString();
    }

    public int leastInterval(char[] tasks, int n) {

        if (n == 0) {
            return tasks.length;
        }
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b) -> (b.freq == a.freq ? a.ch - b.ch : b.freq - a.freq));
        Map<Character, Integer> map = new HashMap();
        for (char ch : tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(new pair(entry.getValue(), entry.getKey()));
        }

        n++;
        int res = 0;
        while (!pq.isEmpty()) {
            int k = Math.min(n, pq.size());
            List<pair> list = new ArrayList();
            for (int i=1 ; i <= k; i++){
                pair node = pq.poll();
                list.add(node);
            }
            for (pair node: list){
                if(node.freq - 1 > 0){
                    pq.offer(new pair(node.freq-1,node.ch));
                }
            }
            if (pq.isEmpty()) {
                res += k;
            } else {
                res += n;
            }
        }
        return res;
    }

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));
        List<int[]> res = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                res.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[res.size()][2]);
    }

    // 3 sum

    public List<List<Integer>> threeSum(int[] nums) {
        // one add two sum;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                twoSum(nums, res, i + 1, -nums[i]);
            }
        }
        return res;
    }

    private void twoSum(int[] nums, List<List<Integer>> res, int start, int target) {
        // use two pointer to check the solution;
        int lo = start;
        int hi = nums.length - 1;
        while (lo < hi) {
            if (lo - 1 >= start && nums[lo - 1] == nums[lo]) {
                lo++;
                continue;
            }
            if (nums[lo] + nums[hi] == target) {
//                List<Integer> temp = new ArrayList<>();
//                temp.add(nums[start - 1]);
//                temp.add(nums[lo]);
//                temp.add(nums[hi]);
//                res.add(temp);
                res.add(Arrays.asList(nums[start - 1], nums[lo++], nums[hi--]));
//                lo++;
//                hi--;
            } else if (nums[lo] + nums[hi] < target) {
                lo++;
            } else {
                hi--;
            }
        }
    }

    //

    public int findLengthOfLCIS(int[] nums) {

        int count = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
            }
        }
        return Math.max(max, count);
    }

    //257

    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }
        helper(root,new StringBuilder());
        return res;
    }

    private void helper(TreeNode root, StringBuilder sb) {
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            res.add(sb.toString());
        } else {
            sb.append(root.val);
            sb.append("->");
            if (root.left != null) {
                helper(root.left, new StringBuilder(sb));
            }
            if (root.right != null) {
                helper(root.right, new StringBuilder(sb));
            }
        }
    }

    // 51. N-Queens
    public List<List<String>> solveNQueens(int n) {

        // backtracking on each row;


        // use the boolean[][] to memorize if there is a queen on i and j;


        // if isValid, put a queen and then next row;
        // a good way is to use the Set to memorize if it is valid;
        // otherwise we could go three directions;
        return null;
     }

     // 42. Trapping Rain Water

    //
    public int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int lo = 0;
        int hi = height.length - 1;
        int lmax = height[lo];
        int rmax = height[hi];
        int res = 0;
        while (lo <= hi) {
            // go with the lo
            if (lmax < rmax) {
                if (height[lo] <= lmax) {
                    res += lmax - height[lo];
                } else {
                    lmax = height[lo];
                }
                lo++;
            }
            // go with the hi
            else {
                if (height[hi] <= rmax) {
                    res += rmax - height[hi];
                } else {
                    rmax = height[hi];
                }
                hi--;
            }
        }
        return res;
    }

    // it is very interesting to see this problem could be solved by monotonic stack;
    public int trap(int[] height) {

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0 ; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int x = stack.pop();
                if (stack.isEmpty()) {
                    continue;
                }
                int high = Math.min(height[stack.peek()] , height[i]) - height[x];
                int width = i - stack.peek() - 1;
                res += high * width;
            }
            stack.push(i);
        }
        return res;
    }



    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int min = 0;
        int max = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        class pair {
            TreeNode node;
            int level;

            private pair(TreeNode node, int level) {
                this.node = node;
                this.level = level;
            }
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> curr = queue.poll();
            TreeNode currNode = curr.getKey();
            map.putIfAbsent(curr.getValue(), new ArrayList<>());
            min = Math.min(min, curr.getValue());
            max = Math.max(max, curr.getValue());
            map.get(curr.getValue()).add(currNode.val);
            if (currNode.left != null) {
                queue.offer(new Pair(currNode.left, curr.getValue() - 1));
            }
            if (currNode.right != null) {
                queue.offer(new Pair(currNode.right, curr.getValue() + 1));
            }
        }


        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    public void helper(TreeNode root, Map<Integer, List<Integer>> map, int level) {
        if (root == null) {
            return;
        }
//        min = Math.min(min, level);
//        max = Math.max(max, level);
        map.putIfAbsent(level, new ArrayList<>());
        helper(root.left, map, level - 1);
        map.get(level).add(root.val);
        helper(root.left, map, level + 1);

    }

    public int numDecodings(String s) {

        // use DP to solve it;
        // two case;
        // dp[i - 1] ; if the s.charAt(i) == 1, 9 (not 0);
        // dp[i - 2] ; if the s.substring(i-1, i+1) = 10 ~ 26;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '1' && chars[i] <= '9') {
                dp[i + 1] += dp[i];
            }

            if (i != 0 ) {
                int temp = Integer.valueOf(s.substring(i - 1, i + 1));
                if (temp >= 10 && temp <= 26) {
                    dp[i + 1] += dp[i - 1];
                }
            }
        }
        return dp[dp.length - 1];
    }


    public String reverseWords(String s) {

        StringBuilder input1 = new StringBuilder();

        input1.append(s);
        input1.reverse();

        String temp = input1.toString();

        String[] words = temp.split(" ", -1);

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("")) {
                StringBuilder input2 = new StringBuilder();

                input2.append(words[i]);
                input2.reverse();
                res.append(input2);
                res.append(' ');
            }
        }
        res.deleteCharAt(res.length());
        return res.toString();
    }

    public String multiply(String num1, String num2) {

        int res = 0;
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                res += (num1.charAt(i) - '0') * (num2.charAt(j) - '0') * (int) Math.pow(10, i+j);
            }
        }
        return String.valueOf(res);
    }


    public static void main(String[] args) {
    //
      R0802 test = new R0802();


  }
}
