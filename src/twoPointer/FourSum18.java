package twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


// Think about this questions;
// Even though they have asked about the four sum, a higher sum should be also similar;
// think about an extentedable method to implement this problem;

public class FourSum18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums,0,4,target);
    }

    public List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target) return res;
        if (k == 2) {
            return TwoSum(nums,target,start);
        } else {
            for (int i = start; i < nums.length; i++) {
                if (i == start || nums[i] != nums[i-1]) {
                    for (var set : kSum(nums,i+1,k-1,target-nums[i])) {
                        res.add(new ArrayList<>(Arrays.asList(nums[i])));
                        res.get(res.size()-1).addAll(set);
                    }
                }
            }
        }
        return res;
    }

    // very traditional two pointers two sum problems
    // start from the boundary and then meet;
    private List<List<Integer>> TwoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start;
        int hi = nums.length -1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum == target) {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
                while (lo < hi && (lo > start && nums[lo] == nums[lo - 1])) lo++;
            }
            else if  (sum < target) {
                lo++;
            } else {
                hi--;
            }

        }
        return res;
    }


    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0; i < nums.length ; i ++) {
            sum += nums[i];
            if (map.containsKey(sum-k))
                count += map.get(sum-k);
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return count;
    }
}
