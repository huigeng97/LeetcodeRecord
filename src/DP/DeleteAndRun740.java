package DP;

public class DeleteAndRun740 {

    public int deleteAndEarn(int[] nums) {

        int[] dp = new int[10001];
        int[] count = new int[10001];

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
            max = Math.max(nums[i],max);
        }

        dp[1] = count[1];
        for (int i = 2; i <= max + 1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + count[i] * i);
        }

        return dp[max+1];
    }
}
