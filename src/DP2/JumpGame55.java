package DP2;

public class JumpGame55 {
    public boolean canJump(int[] nums) {

        int far = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            far = Math.max(far, nums[i] + i);
            if (far <= i) {
                return false;
            }

        }
        return far >= len - 1;
    }
}
