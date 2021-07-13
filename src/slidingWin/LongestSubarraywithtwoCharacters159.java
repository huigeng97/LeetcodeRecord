package slidingWin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestSubarraywithtwoCharacters159 {
    // The idea is to use the sliding window;
    // start from the begining and find the first string with two letters
    // then move the left side untill the first letter all disappear
    // stop when the right size out of the s.length,
    // we could use an array to store the numberl
    public int lengthOfLongestSubstringTwoDistinct1(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int l = 0, r = 1;
        int res = 1;
        map.put(s.charAt(0),0);
        while (r < s.length()) {
            if (map.size()>=2 && !map.containsKey(s.charAt(r))){
                // Please remember this line!
                // Collections contains: list , set , queue; contradict with the map;
                //
                l = Collections.min(map.values())+1;
                map.remove(s.charAt(l-1));
            }
            // now the left in the left side of the substring and right is the right;
            map.put(s.charAt(r),r);
            res = Math.max(res, r-l+1);
            r++;
        }
        return res;
    }

    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int start = 0, end = 0, counter = 0, len = 0;
        while(end < s.length()){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) == 1) counter++;//new char
            end++;
            while(counter > 2){
                char cTemp = s.charAt(start);
                map.put(cTemp, map.get(cTemp) - 1);
                if(map.get(cTemp) == 0){
                    counter--;
                }
                start++;
            }
            len = Math.max(len, end-start);
        }
        return len;
    }
}
