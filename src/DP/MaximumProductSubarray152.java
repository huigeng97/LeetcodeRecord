package DP;

public class MaximumProductSubarray152 {

    // clearly it is a DP problem, to solve this problem;
    // we need to find the state transition function
    public int maxProduct(int[] nums) {

        // positive max and negative max;
        int pMax = 1, nMax = 1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currMax = Math.max(nums[i],Math.max(pMax*nums[i], nMax*nums[i]));
            nMax = Math.min(nums[i],Math.min(pMax*nums[i], nMax*nums[i]));
            pMax = currMax;
            max = Math.max(max, pMax);
        }
        return max;
    }


}
