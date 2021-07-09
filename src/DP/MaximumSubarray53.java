package DP;

public class MaximumSubarray53 {

    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length+1];
        int max = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i-1],0) + nums[i];
            max = Math.max(dp[i],max);
        }
        return max;
    }

    // another method with divide and conquer;

    public int maxSubArray2(int[] nums) {
        return helper(nums,0);
    }

    public int helper(int[] nums, int start) {
        if (start >= nums.length) {
            return Integer.MIN_VALUE;
        }
        int sum = 0;
        int max = nums[start];
        while (start < nums.length && sum >= 0) {
            sum += nums[start++];
            max = Math.max(max, sum);
        }
        return Math.max(max,helper(nums,start));
    }
}
