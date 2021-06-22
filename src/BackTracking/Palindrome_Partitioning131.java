package BackTracking;

import java.util.ArrayList;
import java.util.List;


/// One time pass! Oh Yeah! Keep working!!
public class Palindrome_Partitioning131 {

    List<List<String>> res = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backtrack(s,0,new ArrayList<>());
        return res;
    }

    private void backtrack(String s, int start, ArrayList<String> words) {
        if (start == s.length()) {
            res.add(new ArrayList<>(words));
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s,start,i)) {
                words.add(s.substring(start, i+1));
                backtrack(s,i+1,words);
                words.remove(words.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
        }
        return true;
    }


}
