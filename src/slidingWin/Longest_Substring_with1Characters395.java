package slidingWin;

public class Longest_Substring_with1Characters395 {
    // we could either use the divide conquer or the sliding window;

    // the divide conquer is straightforward;
    public static int longestSubstring(String s, int k) {
        // helper function to check the substring;
        return longestSubstring(s, 0, s.length()-1, k);
    }

    private static int longestSubstring(String s, int start, int end, int k) {
        // helper function to check the substring;
        // create the char map for s.substring(start,end);
        if (start > end) return 0;
        int[] map = new int[26];
        for (char ch : s.substring(start,end+1).toCharArray()) {
            map[ch-'a'] += 1;
        }

        for (int i = start; i <=end; i++) {
            if (map[s.charAt(i)-'a'] < k) {

                // These two lines can speed up the running time from 120ms t0 0ms;
                int midNext = i + 1;
                while (midNext <= end && map[s.charAt(midNext) - 'a'] < k) midNext++;
                // This line will speed the range of the searching;

                int l1 = longestSubstring(s, start, i-1, k);
                int l2 = longestSubstring(s,midNext,end,k);
                return Math.max(l1,l2);
            }
        }
        return end - start + 1;
    }


    // We could also use the sliding window to solve this problem;
    // not quite hard
    public static int longestSubstring2(String s, int k) {
        // first we need to find the unique letters in the s;
        // then we need to start 1 to the UniqueLetters to find the longestString for each situation;
        // in each situation, we need to start from the leftmost one char, move the window till to get a good substring
        // and then either expand or shrink to get another one untill windowEnd = n;
        int res = 0;
        int maxLetter = getMaxLetter(s);

        for (int currNumLetter = 1; currNumLetter <= maxLetter; currNumLetter++) {
            int l = 0, r = 0;
            // substring is [l,r];
            int counter = 0;
            int NumLetterK = 0;
            int[] dict = new int[26];
            while (r < s.length()) {
                int x = s.charAt(r)-'a';
                if (dict[x] == 0) {
                    counter++;
                    while (counter>currNumLetter && l <= r) {
                        int y = s.charAt(l)-'a';
                        if (dict[y] == k) NumLetterK--;
                        dict[y]--;
                        if (dict[y] == 0) counter--;
                        l++;
                    }
                }
                dict[x]++;
                // This line needs to make sure the same letter won't be count twice;
                if (dict[x] == k) NumLetterK++;
                if (currNumLetter == NumLetterK) res = Math.max(res, r-l+1);
                r++;
            }
        }
        return res;
    }


    public static int getMaxLetter(String s) {
        boolean[] map = new boolean[26];
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (!map[ch-'a']) {
                map[ch-'a'] = true;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring2("aaabb",3));
    }
}
