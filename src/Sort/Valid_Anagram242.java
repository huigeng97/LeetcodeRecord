package Sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Valid_Anagram242 {
    public boolean isAnagram(String s, String t) {
        if (s.length()!= t.length()) return false;
        int[] dict = new int[26];
        for (int i = 0 ; i < s.length(); i++) {
            dict[s.charAt(i) - 'a'] += 1;
            dict[t.charAt(i) - 'a'] -= 1;
        }

        for (int i = 0; i < 26 ; i ++) {
            if (dict[i] != 0 ) {
                return false;
            }
        }
        return true;
    }

    // This problem can also be done with the sorting
    // Train for converting from the Char array with the string;
    // A new method learnt to compare two char(/int) array: Arrays.equals(a,b);
    public boolean isAnagram2(String s, String t) {
        if (s.length()!= t.length()) return false;
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
//        String c = new String(a);;
//        String d = new String(b);;
//        if (c.equals(d)) return true;
//        else return false;
        return Arrays.equals(a,b);
     }

}
