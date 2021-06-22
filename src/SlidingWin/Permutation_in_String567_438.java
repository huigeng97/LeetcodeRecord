package SlidingWin;

import java.util.HashMap;
import java.util.Map;

public class Permutation_in_String567_438 {
    public boolean checkInclusion(String s1, String s2) {
        int l = 0;
        int r = 0;
        int counter = s1.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        while (r < s2.length()) {
            // keep update right, if map contains, minus one;
            // if map doesn't contain, left++ until map.contains(ch) or left = r+1;
            // use the char instead of charAt(r/l) is faster;
            char curr = s2.charAt(r);
            if (map.containsKey(curr) && map.get(curr) > 0) {
                map.put(curr, map.get(curr) - 1);
                counter--;
                if (counter == 0) return true;
            } else {
                while (l <= r) {
                    char comp = s2.charAt(l);
                    if (comp == curr) {
                        l++;
                        break;
                    }
                    map.put(comp, map.get(comp) + 1);
                    counter++;
                    l++;
                }
            }
            r++;
        }
        return false;
    }

        // One thing deserve noticing is the inclusion/Anagrams's length equals each others;
        // which means the winders should be a constant length;
        // move the right and left together;

        // slightly change into the array to store the dict;
        // improve from 60% -> 98.98%;
    public boolean checkInclusion2(String s1, String s2) {
        int counter = s1.length();
        int l = 0;
        int r = 0;
        int[] s1Arr = new int[26];
        Map<Character,Integer> map = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            s1Arr[ch-'a'] += 1;
        }

        while (r < s2.length()) {
            // keep update right, if map contains, minus one;
            // if map doesn't contain, left++ until map.contains(ch) or left = r+1;
            char curr = s2.charAt(r);
            if (s1Arr[curr-'a'] >0) {
                s1Arr[curr-'a']--;
                counter--;
                if (counter==0) return true;
            } else {
                while (l<=r) {
                    char comp = s2.charAt(l);
                    if (comp == curr) {
                        l++;
                        break;
                    }
                    s1Arr[comp-'a']++;
                    counter++;
                    l++;
                }
            }
            r++;
        }
        return false;
    }
}
