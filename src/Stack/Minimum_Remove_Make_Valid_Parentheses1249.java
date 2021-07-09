package Stack;

import java.nio.charset.CharsetEncoder;
import java.util.*;

public class Minimum_Remove_Make_Valid_Parentheses1249 {

    // StringBuilder new method :  sb.charAt(i), sb.deleteCharAt(i), sb.setCharAt(i);



    public String minRemoveToMakeValid(String s) {

        Stack<Object> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (Character ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(sb);
                stack.push(ch);
                sb = new StringBuilder();
            } else if (ch == ')') {
                if (!stack.isEmpty()&&stack.peek().getClass().equals(Character.class)) {
                    stack.pop();
                    StringBuilder temp = (StringBuilder) stack.pop();
                    temp.append('(');
                    temp.append(sb);
                    temp.append(ch);
                    sb = temp;
                }
            } else {
                sb.append(ch);
            }
        }
        stack.push(sb);
        StringBuilder res = new StringBuilder();
        for (Object obj : stack) {
            if (!obj.getClass().equals(Character.class)) {
                res.append(obj);
            }
        }
        return res.toString();
    }

    public String minRemoveToMakeValid2(String s) {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> delete = new HashSet<>();
        int balance = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                balance++;
                stack.push(i);
            } else if (ch == ')') {
                if (balance > 0) {
                    stack.pop();

                } else {
                    delete.add(i);
                }
            }
        }

        while (!stack.isEmpty()) {
            delete.add(stack.pop());
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!delete.contains(i)) {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

  public static void main(String[] args) {

        //
      Minimum_Remove_Make_Valid_Parentheses1249 test = new Minimum_Remove_Make_Valid_Parentheses1249();
      test.minRemoveToMakeValid("lee(t(c)o)de)");
  }

  // the fastest method is to use two pass,
    // first to remove the invalid )
    // second to remove the rightmost (;
}
