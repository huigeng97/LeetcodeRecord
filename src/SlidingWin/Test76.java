package SlidingWin;

import java.util.HashMap;
import java.util.Map;

public class Test76 {
    // It would be faster if we use the array to memorize the char frequency;
    public String minWindow(String s, String t) {
        String res = "";
        if (t.length() > s.length()) return res;
        Map<Character, Integer> dict = new HashMap<>();
        int count=t.length();
        for (char ch : t.toCharArray()) {
            dict.put(ch, dict.getOrDefault(ch,0)+1);
        }
        int start=0, end=0, d=Integer.MAX_VALUE, head=0;
        while (end < s.length()) {
            if (dict.containsKey(s.charAt(end))) {
                if (dict.get(s.charAt(end)) > 0) {
                    count--;
                }
                dict.put(s.charAt(end), dict.get(s.charAt(end)) - 1);
            }
            end++;
            while (count == 0) {
                if (end - start < d) {
                    res = s.substring(start,end);
                    d = end - start;
                }
                // make it invalid;
                // notice that we could seize the essence????
                // to make it invalid, we only care if the counter is positive or not;
                // we don't care about the char at end if it is changed;
                if (dict.containsKey(s.charAt(start))) {
                    if (dict.get(s.charAt(start)) >= 0) {
                        count++;
                    }
                    dict.put(s.charAt(start), dict.get(s.charAt(start)) + 1);
                }
                start++;
            }
        }
        return res;
    }
}
