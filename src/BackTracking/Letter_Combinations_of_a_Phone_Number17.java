package BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Letter_Combinations_of_a_Phone_Number17 {

    // This implement is to use the queue since it is FIFO;
    // peek one and then propagate it with one for loop and one while lop until we got the end;
    public List<String> letterCombinations(String digits) {

        LinkedList<String> res = new LinkedList<>();
        if (digits.length() == 0) return res;
        res.add("");
        for (int i =0; i<digits.length();i++) {
            while(res.peek().length()==i){
                String t = res.remove();
                for (char ch : mapping[digits.charAt(i)-'0'].toCharArray()) {
                    res.addLast(t + String.valueOf(ch));
                }
            }
        }
        return  res;
    }


    // This is a traditional Backtracking method that I used the first time I start the leetcode;
    private List<String> res = new ArrayList<>();
    private String digits;
    String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations2(String digits) {
        if (digits.length() == 0) {
            return res;
        }
        this.digits = digits;
        backtrack(0, new StringBuilder());
        return res;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == digits.length()) {
            res.add(path.toString());
            // 满足条件加入到结果中；
            return; // Backtrack
        }
        String Letters = mapping[digits.charAt(index)];
        for (char letter : Letters.toCharArray()) {
            path.append(letter);
            backtrack(index+1,path);
            path.deleteCharAt(index);
        }
    }

}
