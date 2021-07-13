package DP2;

public class HouseRobber198 {
    public int rob(int[] nums) {

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
        }
        return dp[dp.length - 1];
    }


    // Double beat 100%

    public int rob2(int[] nums) {
        int old = 0;
        int now = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = now;
            now = Math.max(old + nums[i], now);
            old = temp;
        }
        return now;
    }
}
