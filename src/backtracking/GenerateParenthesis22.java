package backtracking;

import java.util.LinkedList;
import java.util.List;

public class GenerateParenthesis22 {
    LinkedList<String> res = new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        backtrack("", 0, 0, n);
        return res;
    }

    private void backtrack(String path, int open, int close, int n) {
        if (path.length() == 2 * n) {
            res.add(path.toString());
            return;
        }

        if (open < n) {
            backtrack(path+"(",open+1,close,n);
        }

        if (close < open) {
            backtrack(path+")",open,close+1,n);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis22 test = new GenerateParenthesis22();
        test.generateParenthesis(3);
    }
}
