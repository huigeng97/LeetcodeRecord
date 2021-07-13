package hash;

import java.util.Arrays;

public class MinimumDeletionsMakeCharacterFrequenciesUnique1647 {

    // the idea is to use an array to store the string
    // and then sort the array and one pass to make the result valid;
    public int minDeletions(String s) {
        int[] dict = new int[26];
        for (char ch : s.toCharArray()) {
            dict[ch-'a']++;
        }
        Arrays.sort(dict);
        int count = 0;
        for (int i = dict.length-2; i >= 0; i--) {
            if (dict[i] >= dict[i+1]) {
                int diff = dict[i] - (dict[i+1] == 0 ? 0 : dict[i+1]-1);
                count += diff;
                dict[i] -= diff;
            }
        }
        return count;
    }
}
