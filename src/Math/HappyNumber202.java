package Math;

/**
 * @author Geng Hui
 */
public class HappyNumber202 {

    // This problem is actually harder than I thought;
    // it looks like a math problem but it is a linkedList problem;
    // we need to detect if there is a cycle inside this process;

    public int getNext(int n) {
        int num = 0;
        while (n > 0) {
            num += Math.pow(n % 10,2);
            n = n / 10;
        }
        return num;
    }

    public boolean isHappy2(int n) {
        int fast = getNext(n);
        int slow = n;
        while (fast != slow) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);
            if (slow == 1) {
                return true;
            }
        }
        return false;
    }


    public boolean isHappy(int n) {

        if (0 <= n && n < 5) {
            return n == 1;
        }
        int num = 0;
        while (n > 0) {
            num += Math.pow(n % 10,2);
            n = n / 10;
        }
        return isHappy(num);
    }

}
