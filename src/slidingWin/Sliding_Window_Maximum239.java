package slidingWin;

import java.util.ArrayDeque;
import java.util.Deque;


// Don't think this is a Twopointer problem but a DP problem;???
// not classical DP;
// Actually we could use an variant of the sliding window ( Deque ) to finish the problem;
public class Sliding_Window_Maximum239 {


    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        // use the constant sliding window;
        int ri = 0;
        Deque<Integer> q = new ArrayDeque<>();
        int i = 0;
        while (i < nums.length) {
            // each time we poll the current number into the q if it is larger than the last one;
            // if the first one is out of the range, we just discard it;
            // also peek the first one into the res;
            while (nums[i] > nums[q.peekLast()]) {
                q.pollFirst();
            }
            q.offerLast(i);
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }
            if (i >= k - 1) {
                res[ri++] = nums[q.peekLast()];
            }
        }
        return res;
    }

        // Finally! Please 理清思路！ 大的在头（PeekFirst）, 小的在对尾（OfferLast）
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        // use the constant sliding window;
        int ri = 0;
        Deque<Integer> q = new ArrayDeque<>();
        int i = 0;
        while (i < nums.length) {
            // each time we poll the current number into the q if it is larger than the last one;
            // if the first one is out of the range, we just discard it;
            // also peek the first one into the res;
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()]) {
                q.pollLast();
            }
            q.offerLast(i);
            if (i >= k - 1) {
                res[ri++] = nums[q.peekFirst()];
            }
            i++;
        }
        return res;
    }

    private int calculateMax(int[] nums, int l, int k) {
        int max = nums[l];
        for (int i = l+1; i < l+k; i++) {
            max =Math.max(max,nums[i]);
        }
        return max;
    }
}
