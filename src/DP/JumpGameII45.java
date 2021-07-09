package DP;


/**
 * @author Geng Hui
 */
public class JumpGameII45 {

    public int jump(int[] nums) {
        // use dp to store the minimal jump number;
        // dp[i] = Minimum of dp[i-k], where k <= nums[i-k];

        // Time clxy O(n2), space O(n);
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int k = 1; k <= nums[i]; k++) {
                if (i+k < dp.length) {
                    if (dp[i+k] != 0) {
                        dp[i+k] = Math.min(dp[i+k], dp[i] + 1);
                    } else {
                        dp[i+k] = dp[i] + 1;
                    }
                }
            }
        }
        return dp[nums.length-1];
    }

    public int jump2(int[] nums) {
        if (nums.length <= 1) return 0;
        int[] dp = new int[nums.length];
        int maxJump = 0;
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i > dp[maxJump]) {
                maxJump++;
            }
            dp[maxJump+1] = Math.max(dp[maxJump+1], i + nums[i]);
            // compare approach
        }
        return maxJump + 1;
    }

    // actually it is a greedy problem;


    public int jump3(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }


}
