package SlidingWin;

import java.util.HashMap;
import java.util.Map;

public class Longest_Substring_Without_Repeating_Characters3 {
    public int lengthOfLongestSubstring(String s) {
        int lo = 0; int hi = 0;
        int res = 0;
        int[] dict = new int[128];
        while (hi < s.length()) {
            while (dict[s.charAt(hi)] != 0 && lo <= hi) lo++;
            dict[s.charAt(hi)] += 1;
            res = Math.max(res, hi - lo + 1);
            hi++;
        }
        return res;
    }

    // we could also optimize the speed of the lo by storing the last occurred position + 1;
    // then we don;t have to update the lo;
    public int lengthOfLongestSubstring2(String s) {
        int lo = 0; int hi = 0;
        int res = 0;
        Map<Character, Integer> dict = new HashMap<>();
        while (hi < s.length()) {
            if (dict.containsKey(s.charAt(hi))) {
                lo = Math.max(lo, dict.get(s.charAt(hi)));
            }
            res = Math.max(res, hi - lo + 1);
            // return hi+1;
            dict.put(s.charAt(hi), ++hi);
        }
        return res;
    }

}
