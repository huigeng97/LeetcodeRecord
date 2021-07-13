package twoPointer;

import java.util.*;

public class ThreeSum15 {
    // the binary search is not helping improving the running time, only works for some specific cases;
    // don't use it without more information;
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-1 && nums[i] <= 0; i++) {
            if (i != 0 && nums[i] == nums[i-1] ) continue;
            // do the two sum problem in the following!
            int left = i+1;
            int right = nums.length-1;
            while (left < right) {
                int mid = (left + right) / 2;
                int sum = nums[i] + nums[left] + nums[right];
                /// This is a three condition case
                if (sum < 0) {
                    left = (nums[mid] + nums[right] + nums[i] < 0) ? mid + 1 : left + 1;
                } else if (sum > 0) {
                    right = (nums[mid] + nums[left] + nums[i] > 0) ? mid - 1 : right - 1;
                }
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    // this line is very important because it is hard to make it right;
                    // if we find a solution, as we fix i and leave left and right to move; if nums[left] == nums[left+1];
                    // we could make sure that the solution is duplicate, so we have to make sure the nums[left] != nums[left+1];
                    // also we do the while loop in while ,we have to make and the first condition inside the nested while loop;
                    while (left < right && nums[left] == nums[left - 1])
                        ++left;
                }

            }
        }
        return res;
    }
}
