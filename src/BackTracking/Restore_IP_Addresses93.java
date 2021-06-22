package BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



// Learn a new method of String:
// Build a "temp" list of Strings and then join them by String.join(".",temp);
public class Restore_IP_Addresses93 {
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        backtack(s, 0, new LinkedList<String>(), 4);
        return res;
    }

    private void backtack(String s, int start, LinkedList<String> temp, int partNum) {
        if (partNum == -1) return;
        if (start == s.length()) {
            if (partNum == 0) res.add(String.join(".",temp));
        }
        for (int i = start ; i < s.length() && i < start + 3; i++) {
            if (isValid(s.substring(start,i+1))) {
                temp.add(s.substring(start, i+1));
                backtack(s,i+1,temp, partNum - 1);
                temp.removeLast();
            }
            if (s.charAt(start) == '0') break;
        }
    }

    private boolean isValid(String substring) {
        int value = Integer.valueOf(substring);
        if (value<=255 && value>=0) return true;
        return false;
    }
}
