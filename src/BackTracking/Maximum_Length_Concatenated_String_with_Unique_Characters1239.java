package BackTracking;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Maximum_Length_Concatenated_String_with_Unique_Characters1239 {

    int max = 0;
    public int maxLength(List<String> arr) {
        backtack(arr, new HashSet<Character>(), 0);
        return max;

    }

    private void backtack(List<String> arr, HashSet<Character> set, int index) {
        if (index > arr.size()) {max = Math.max(max,set.size()); return;}
        boolean valid = true;
        HashSet<Character> newSet = new HashSet<>(set);
        for (char ch : arr.get(index).toCharArray()) {
            if (set.contains(ch)) {
                valid = false;
            }
            newSet.add(ch);
        }
        if (valid) {
            backtack(arr, newSet, index+1);
        }
        backtack(arr, set, index+1);
    }




}
