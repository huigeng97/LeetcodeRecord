package DP2;

import DP.JumpGameII45;

import java.util.Arrays;

public class MinimumNumberTapsttoWateraGarden1326 {

    class Tap {
        int left;
        int right;
        public Tap(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    public int minTaps2(int n, int[] ranges) {
        Tap[] taps = new Tap[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            taps[i] = new Tap(i - ranges[i], i + ranges[i]);
        }
        Arrays.sort(taps, (a, b) -> (a.left - b.left));

        int currLeft = 0;
        int nextLeft = -1;
        int maxRight = -1;
        int i = 0;
        int res = 0;
        while (i < ranges.length) {
            while (i < ranges.length && taps[i].left <= currLeft) {
                if (maxRight <= taps[i].right) {
                    maxRight = taps[i].right;
                    nextLeft = taps[i].right;
                }
                i++;
            }
            if (maxRight == currLeft) {
                return -1;
            }
            currLeft = nextLeft;
            res++;
            if (maxRight >= n) {
                return res;
            }
        }
        return res;
    }

    public int minTaps1(int n, int[] A) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 2);
        dp[0] = 0;
        for (int i = 0; i <= n; ++i) {
            for (int j = Math.max(i - A[i] + 1, 0); j <= Math.min(i + A[i], n); ++j) {
                dp[j] = Math.min(dp[j], dp[Math.max(0, i - A[i])] + 1);
            }
        }
        return dp[n]  < n + 2 ? dp[n] : -1;
    }

    // Another way is to reduce this problem into Jump Game;

    public int minTaps(int n, int[] ranges) {
        int[] arr = new int[ranges.length];
        for (int i = 0; i < arr.length; i++) {
            int left = Math.max(0, i - ranges[i]);
            arr[left] = Math.max(arr[left], i + ranges[i]);
        }

        int right = arr[0];
        int far = arr[0];
        int res = 1;
        for (int i = 1; i < arr.length; i++) {
            if (right >= n) {
                return res;
            }
            if (right < i) {
                right = far;
                res++;
            }
            if (far < i) {
                return -1;
            } else {
                far = Math.max(far, arr[i]);
            }
        }

        return res;
    }

  public static void main(String[] args) {
    //
      MinimumNumberTapsttoWateraGarden1326 test = new MinimumNumberTapsttoWateraGarden1326();
      System.out.println(test.minTaps(8, new int[]{4,0,0,0,0,0,0,3}));
  }
}
