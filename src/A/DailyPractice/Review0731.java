package A.DailyPractice;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class Review0731 {



    public int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i<length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }



    public boolean isThree(int n) {

        if (n <= 2) return false;
        boolean isValid = false;
        int sqrt = 1;
        for (int i = 1; i <= 100; i++) {
            if (i * i == n) {
                isValid = true;
                sqrt = i;
            }
        }
        if (!isValid) {
            return false;
        } else {
            for (int i = 2; i <= sqrt/2; i++) {
                if (sqrt % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public long numberOfWeeks(int[] milestones) {


        long max = 0;
        long sum = 0;
        for (int i = 0; i < milestones.length; i++) {
            sum += milestones[i];
            max = Math.max(max, milestones[i]);
        }

        if (max > sum / 2) {
            return 2 * (sum - max) + 1;
        } else {
            return sum;
        }
    }

    public long minimumPerimeter(long neededApples) {
        int hi = 10^5;
        int lo = 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            long apple = 2 * mid * (mid + 1) * (2 * mid + 1);
            if (apple < neededApples) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    final int mol = 1000000000 + 7;
    public int countSpecialSubsequences(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums[0] == 0) dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                dp[i] = (2 * dp[i-1]  + 1) % mol;
            } else {
                dp[i] = dp[i-1];
            }
        }

        int[] dp2 = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 1 && dp[i] > 0) {
                dp2[i] = (2 * dp2[i-1]) % mol + dp[i - 1] % mol;
            } else {
                dp2[i] = dp2[i-1];
            }
        }

        int[] dp3 = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 2 && dp2[i] > 0) {
                dp3[i] = (2 * dp3[i-1]) % mol + dp2[i - 1] % mol;
            } else {
                dp3[i] = dp3[i-1];
            }
        }
        return dp3[nums.length - 1];
    }

    @Test
    public void testCode() {
        Review0731 test = new Review0731();
        assertEquals(1,1);
    }
    public static void main(String[] args) {

        Review0731 test = new Review0731();
//        for (int i = 1; i <= 36; i++) {
//            System.out.println("i : " + i + " " + test.isThree(i));
//        }
    }
}
