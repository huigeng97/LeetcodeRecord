package Stack;

import java.util.Stack;

public class Test394 {
    public String decodeString(String s) {
        int num = 0;
        Stack<StringBuilder> stack = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)){
                num = num * 10 + ch - '0';
            }
            else if (ch == '[') {
                stack.push(sb);
                nums.push(num);
                sb = new StringBuilder();
                num = 0;
            }
            else if (ch == ']') {
                int times = nums.pop();
                StringBuilder newSb = stack.pop();
                for (int i = 0; i < times; i++) {
                    newSb.append(sb);
                };
                sb = newSb;

            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack st = new Stack();
        st.push(1);
        st.push(2);
        System.out.println(st.pop());
    }
}
