package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculator224 {


//    public int calculate(String s) {
//
//        int operand = 0;
//        int n = 0;
//        Stack<Object> stack = new Stack<Object>();
//
//        for (int i = s.length()-1; i >= 0; i--) {
//            char ch = s.charAt(i);
//
//            if (Character.isDigit(ch)) {
//
//                // Forming the operand - in reverse order.
//                // n is the number of the digit in a number;
//                operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
//                n += 1;
//            } else if (ch != ' ') {
//                // if n != 0, means we have encounter the whole number
//                // we put the number into the stack
//                // then we see next is a what kind of operator;
//                if (n != 0) {
//                    stack.push(operand);
//                    n = 0;
//                    operand = 0;
//                }
//
//                // different operator will have the different operation
//                if (ch == '(') {
//                    //we need to do some calculate for the number int the stack;
//                    // from the top to the bottom;
//                    int res = evaluateExpr(stack);
//                    stack.pop();
//                    stack.push(res);
//                } else {
//                    // For other non-digits just push onto the stack. like (+) (-);
//                    stack.push(ch);
//                }
//            }
//        }
//    }
//
//    private int evaluateExpr(Stack<Object> stack) {
//            int res = 0;
//            if (!stack.empty()) {
//                res = (int) stack.pop();
//            }
//
//            while (!stack.empty() && !((char) stack.peek() == ')')) {
//
//            }
//            return res;
//    }


    // 这道题可以用recursive not stack to implement;
    // because recursive in natural still uses the stack to implement;
    // " recursive stack ";

    // 这道题的核心在于怎么处理加减号
    // 因为可能出现重复的符号，所以需要在iteration 判断char是什么的时候就确定了，而且只取最后一个符号；
    // 不要一股脑都加入到stack 然后重复判断是数字还是符号；
    int i = 0;

    public int calculate(String s) {

        int nextOperationSign = 1;
        int res = 0;
        int operand = 0;

        while (i < s.length()) {
            char ch = s.charAt(i++);

            if (ch == '(') {
                operand = calculate(s);
            }
            else if (ch == ')') {
                break;
            }
            else if (ch == ' ') {
                continue;
            }
            else if (Character.isDigit(ch)) {
                operand = 10 * operand + ch - '0';
            } else {
                res += nextOperationSign*operand;
                operand = 0;
                nextOperationSign = ch == '+' ? 1 : 0;
            }
        }
        return res += nextOperationSign*operand;
    }

}
