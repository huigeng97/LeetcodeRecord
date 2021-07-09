package DFS;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers386 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (i <= n) {
                dfs(i,n,res);
            }

        }
        return res;
    }

    private void dfs(int num, int n, List<Integer> res) {
        // use the  dfs to traverse the lexicographical tree;
        // the order is node, left, right;
        // first node,
        res.add(num);

        for (int i = 0; i < 10; i++) {
            int next = num * 10 + i;
            if (next <= n) {
                dfs(next,n,res);
            }
        }
    }
}
