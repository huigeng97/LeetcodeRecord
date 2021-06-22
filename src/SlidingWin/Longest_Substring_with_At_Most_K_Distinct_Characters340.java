package SlidingWin;

public class Longest_Substring_with_At_Most_K_Distinct_Characters340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] dict = new int[128];
        int l = 0;
        int r = 0;
        int res = 0;
        int counter = 0;
        // start from the leftmost one;
        // k = counter then update the res;
        while (r < s.length()) {
            int rchar = s.charAt(r);
            if (dict[rchar] == 0) counter++;
            dict[rchar]++;

            while (counter > k) {
                // move the left untill counter == k;
                int lchar = s.charAt(l);
                dict[lchar]--;
                if (dict[lchar] == 0) counter--;
                l++;
            }
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }
}
