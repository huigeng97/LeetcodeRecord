package A.DailyPractice;

import java.util.*;

public class Review0721 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        HelperKSum(nums, target, new ArrayList<Integer>(), 4, 0);
        return res;
    }

    private void HelperKSum(int[] nums, int target, List<Integer> temp, int K, int start) {
        if (start == nums.length || nums[start] * K > target) {
            return;
        }
        if (K == 2) {
            if (start >= nums.length - 1) {
                return;
            } else {
                // do 2 sum equals to target;
                TwoSum(nums, target, temp, start);
            }

        } else {
            for (int i = start; i < nums.length; i++) {
                if (i == start || nums[i] != nums[i - 1]) {
                    temp.add(nums[i]);
                    HelperKSum(nums, target - nums[i], temp,K - 1, i + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    private void TwoSum(int[] nums, int target, List<Integer> temp, int start) {
        // deal with the corner case that [3,3,3,2,2,4,4];
        // only return [3,3] [2,4];
        int lo = start;
        int hi = nums.length -1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum == target) {
                List<Integer> curr = new ArrayList<>();
                curr.addAll(temp);
                curr.add(nums[lo]);
                curr.add(nums[hi]);
                res.add(curr);
                while (lo < hi && (lo > start && nums[lo] == nums[lo - 1])) {
                    lo++;
                }
            }
            else if  (sum < target) {
                lo++;
            } else {
                hi--;
            }

        }
    }
}
