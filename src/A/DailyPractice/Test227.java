package A.DailyPractice;

public class Test227 {

    int i = 0;
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int oper = 0;
        int num = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            else if (s.charAt(i) == '+') {
                oper += calculate(s);
            }
            else if (s.charAt(i) == '-') {
                oper -= calculate(s);
            }
            else if (s.charAt(i) == '*') {
                oper *= nextNumber(s);
            }
            else if (s.charAt(i) == '/') {
                oper /= nextNumber(s);
            } else {
                num = num * 10 + s.charAt(i) - '0';
            }
            i++;
        }
        return oper;
    }

    private int nextNumber(String s) {
        i += 1;
        while (s.charAt(i) == ' ') {
            continue;
        }
        int num = 0;
        while (Character.isDigit(s.charAt(i))) {
            num = num * 10 + s.charAt(i) - '0';
            i++;
        }
        return num;
    }
}
