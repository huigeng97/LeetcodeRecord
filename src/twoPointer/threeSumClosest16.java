package twoPointer;

import java.util.Arrays;

public class threeSumClosest16 {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length; i ++) {
            int lo = i + 1, hi = nums.length -1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff) ) {
                    diff = target - sum;
                }
                if (sum < target) lo ++;
                else hi --;
            }
        }
        return target - diff;
    }
}
