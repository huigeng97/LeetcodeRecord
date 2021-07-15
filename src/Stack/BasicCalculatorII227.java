package Stack;

import java.util.Stack;

public class BasicCalculatorII227 {



    public int calculate(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i))) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }

    // without stack (space optimality)
    public int calculate2(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        int len = s.length();
        int curr = 0, prev = 0;
        char sign = '+';
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                curr = curr * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i))) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (sign == '-') {

                    res += prev;
                    prev = -curr;
                }
                if (sign == '+') {
                    res += prev;
                    prev = curr;
                }
                if (sign == '*') {
                    prev *= curr;
                }
                if (sign == '/') {
                    prev /= curr;
                }
                sign = s.charAt(i);
                curr = 0;
            }
        }
        return res += prev;
    }

}
