package BackTracking;

import java.util.HashSet;
import java.util.Map;

public class Regular_Expression_Matching10 {

    // we should use the Dp instead of the backtracking!
    // !!!! If the method is wrong, it is almost impossible to make it!

    //--------------------------------------------------------------------;

    // backtrack all the combination of the p
    // if one of them equals s return true;
    // else return false;
    // use the backtrack to generate the string and get rid of the (*);
    // use the isEqual to compare two Strings;
    public boolean isMatch(String s, String p) {
        return false;

    }



    public boolean isEquals(String s, StringBuilder curr) {
        String p = new String(curr);
        if (p.length() != s.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (p.charAt(i) == '.') continue;
            if (p.charAt(i) != s.charAt(i)) return false;
        }
        return true;
    }
}
