package Stack;

import java.util.Stack;

public class ValidParentheses20 {

    // Think about the corner case before the test;
    // what if stack.pop() == null;
    // the good way is to always think about what the error message that one method / calling could pop up;
    // if there could be one, just eliminate it!!!
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')' ) {
                if (stack.pop() != '(') {
                    return false;
                }
            } else if (ch == '}' ) {
                if (stack.pop() != '{') {
                    return false;
                }
            } else if (ch == ']' ) {
                if (stack.pop() != '[') {
                    return false;
                }
            } else stack.push(ch);
        }

        return true;
    }

    // slightly better implementation;
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

}
