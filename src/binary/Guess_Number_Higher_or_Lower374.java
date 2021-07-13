package binary;

/// Not deserve to solve it;
// small trick; int mid = (hi+lo) >>> 1; faster than int mid = (hi + lo) / 2 and without causing overflow;
// >> is arithmetic right shift and >>> is the logical right shift
// (Integer.MAX_VALUE+Integer.MAX_VALUE+2)>>>1 == 0;
// (Integer.MAX_VALUE+Integer.MAX_VALUE+1)>>>1 == Integer.MAX_VALUE;
// (Integer.MAX_VALUE+Integer.MAX_VALUE)>>>1 == Integer.MAX_VALUE;
public class Guess_Number_Higher_or_Lower374 {
    public class Solution extends GuessGame {
        public int guessNumber(int n) {
            int lo = 1, hi = n;

            while (lo <= hi) {
                int mid = (hi+lo) >>> 1;
                if (guess(mid) == 0) {
                    return mid;
                } else if (guess(mid) > 0) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return hi;

        }
    }



    private class GuessGame {
        int pick = 6;
        public int guess(int mid) {
            if (pick <mid) {
                return -1;
            } else if (pick == mid) {
                return 0;
            } else return 1;
        }
    }
}
