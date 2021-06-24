package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DecodeString394 {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ']') {
                List<Character> decodeString = new ArrayList<>();

                while (stack.peek() != '[') {
                    decodeString.add(stack.pop());
                }
                stack.pop();
                int base = 1;
                int k = 0;

                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop()-'0') * base;
                    base *= 10;
                }

                while (k != 0) {
                    // stack.push(decodeString.toString() * k)
                    for (int j = decodeString.size() - 1; j >= 0; j--) {
                        stack.push(decodeString.get(j));
                    }
                    k--;
                }
            }
            else {
                stack.push(s.charAt(i));
            }
        }
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }

    public String decodeString2(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k*10 + (ch - '0');
                // if ch == [ we push k to the countStack to memorize the '['
            } else if (ch == '[') {
                countStack.push(k);
                k = 0;
                // notice that we push the number and previous String at the same time;
                stringStack.push(currentString);
                currentString = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // it is the time to pop the currentString and appending currentString k times
                int times = countStack.pop();
                for (int i = 0; i < times; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                // append the ch;
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }






























}
