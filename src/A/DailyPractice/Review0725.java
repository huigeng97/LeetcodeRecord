package A.DailyPractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Review0725 {
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        int length = words[0].length();
        for (int i = 0; i < s.length() - length * words.length + 1; i++) {
            if (i + length <= s.length() && map.containsKey(s.substring(i, i + length))) {
                // System.out.println(i);
                // System.out.println(map);
                if (helper(s, i, map, length, words.length)) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    private boolean helper(String s, int start, HashMap<String, Integer> map, int length, int count) {

        if (count == 0) {
            return true;
        } else if (start + length > s.length()) {
            return false;
        } else {
            String temp = s.substring(start, start + length);
            if (map.containsKey(temp)) {
                if (map.get(temp) > 0) {
                    map.put(temp, map.get(temp) - 1);
                    if (helper(s, start + length, map, length, count - 1)) {
                        map.put(temp, map.get(temp) +1);
                        return true;
                    }
                    map.put(temp, map.get(temp) +1);
                }
            }
        }
        return false;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        final Map<String, Integer> counts = new HashMap<>();
        for (final String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        final List<Integer> indexes = new ArrayList<>();
        final int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i < n - num * len + 1; i++) {
            final Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                final String word = s.substring(i + j * len, i + (j + 1) * len);
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == num) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    // 其实这道题是一道双指针题；

}
