package Stack;

import java.util.Stack;

public class Remove_All_Adjacent_Duplicates_String_II {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek()[0] == (ch)) {
                int count = stack.peek()[1] + 1;
                stack.pop();
                if (count < 3) {
                    stack.push(new int[]{ch,count});
                }
            }
            else stack.push(new int[]{ch,0});
        }
        StringBuilder sb = new StringBuilder();
        for (int[] entry : stack) {
            for (int i = 0; i< entry[1]; i ++) {
                sb.append((char) entry[0]);
            }
        }
        return new String(sb);
    }


    // we could create a class to speed up the searching
    class Pair {
        int cnt;
        char ch;
        public Pair(int cnt, char ch) {
            this.ch = ch;
            this.cnt = cnt;
        }
    }
    public String removeDuplicates2(String s, int k) {
        Stack<Pair> counts = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (counts.empty() || s.charAt(i) != counts.peek().ch) {
                counts.push(new Pair(1, s.charAt(i)));
            } else {
                if (++counts.peek().cnt == k) {
                    counts.pop();
                }
            }
        }
        StringBuilder b = new StringBuilder();
        while (!counts.empty()) {
            Pair p = counts.pop();
            for (int i = 0; i < p.cnt; i++) {
                b.append(p.ch);
            }
        }
        return b.reverse().toString();
    }


    public String removeDuplicates3(String s, int k) {
        Stack<Integer> counts = new Stack<>();
        char[] sa = s.toCharArray();
        int j = 0;
        for (int i = 0; i < s.length(); ++i, ++j) {
            sa[j] = sa[i];
            if (j == 0 || sa[j] != sa[j - 1]) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    j = j - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return new String(sa, 0, j);
    }
}
