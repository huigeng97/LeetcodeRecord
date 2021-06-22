package SlidingWin;

import java.util.HashMap;
import java.util.Map;

public class Minimum_Window_Substring76 {
    public String minWindow(String s, String t) {
        int l = 0, r = 0;
        int[] map = new int[128];
        int[] Oldmap = new int[128];
        for (char ch : t.toCharArray()) {
            map[ch]++;Oldmap[ch]++;
        }
        int length = Integer.MAX_VALUE;
        String res = "";
        int counter = t.length();
        while (r < s.length()) {
            char currR = s.charAt(r);
            if (map[currR]>0) {
                // (Very Important!!!!!!!!)
                // The counter all memory how many letter we miss
                // we don't need to memory the excessive letter;
                counter--;
            }
            map[currR]--;
            // (Very Important!!!!!!!!)
            // map record all the letters not only the char in the t but also in the s; (Very Important!!!!!!!!)
            // because the letter not in the t will never have a positive frequency, we will never need to worry about it;
            while (counter == 0) {
                if (r - l + 1 < length) {
                    length = r-l+1;
                    res = s.substring(l,r+1);
                }
                // make it invalid; // the least valid substring will be the shortest string;
                if(map[s.charAt(l)]==0) counter++;
                map[s.charAt(l)]++;
                l++;
            }
            r++;
        }
    return res;
    }

    // This is a template for sliding window problem;
    public String minWindow2(String s, String t) {
        int[] map = new int[128];
        for (char ch : t.toCharArray()) {
            map[ch]++;
        }
        int counter=t.length(), begin=0, end=0, d=Integer.MAX_VALUE, head=0;
        while(end<s.length()){
            if(map[s.charAt(end++)]-->0) counter--; //in t
            while(counter==0){ //valid
                if(end-begin<d)  d=end-(head=begin);
                if(map[s.charAt(begin++)]++==0) counter++;  //make it invalid
            }
        }
        return d==Integer.MAX_VALUE? "":s.substring(head, head+d);
    }

    public static void main(String[] args) {
        new Minimum_Window_Substring76().minWindow("ADOBECODEBANCD","ABC");
    }
}
