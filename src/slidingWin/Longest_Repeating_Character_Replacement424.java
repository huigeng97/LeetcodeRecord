package slidingWin;

public class Longest_Repeating_Character_Replacement424 {

    /// Still not quite understand the sliding window;
    public static int characterReplacement(String s, int k) {
        int[] cArr = new int[26];
        int maxCount = 0, start = 0, maxSize = 0;

        for(int end = 0; end < s.length(); end ++){
            cArr[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, cArr[s.charAt(end) - 'A']);

            // if max character frequency + distance between start and end is greater than k
            // this means we have considered changing more than k charactres. So time to shrink window
            if(end - start + 1 - maxCount > k){
                cArr[s.charAt(start) - 'A']--;
                start ++;
            }
            // The length of the window has fixed once we find the best solution;
            // It will never shrink;
            // An easier solution would be tracked the changed letter by calculate the sum of the array;

            // if you understand last command, you will find we don't need to memorize the maxSize;
            if (maxSize <= end - start + 1) {
                maxSize = Math.max(maxSize, end - start + 1);
                System.out.println(end - start + 1);
            };
        }

        return maxSize;
    }

    // Contribute by Lee;
    // This is a very concise solution; and fastest one;
    public int characterReplacement2(String s, int k) {
        int maxf = 0, i = 0, n = s.length(), count[] = new int[26];
        for (int j = 0; j < n; ++j) {
            maxf = Math.max(maxf, ++count[s.charAt(j) - 'A']);
            if (j - i + 1 > maxf + k)
                --count[s.charAt(i++) - 'A'];
        }
        return n - i;
    }

    public static void main(String[] args) {
        characterReplacement("abbbcbbedeeddd".toUpperCase(),4);
    }
}
