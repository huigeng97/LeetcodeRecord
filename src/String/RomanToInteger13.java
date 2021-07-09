package String;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Geng Hui
 */
public class RomanToInteger13 {

    static Map<Character, Integer> values = new HashMap<>();

    static {
        values.put('M', 1000);
        values.put('D', 500);
        values.put('C', 100);
        values.put('L', 50);
        values.put('X', 10);
        values.put('V', 5);
        values.put('I', 1);
    }

    public int romanToInt(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if ( i != s.length()-1 && values.get(s.charAt(i)) < values.get(s.charAt(i+1))) {
                num -= values.get(s.charAt(i));
            } else {
                num += values.get(s.charAt(i));
            }
        }
        return num;
    }

}
