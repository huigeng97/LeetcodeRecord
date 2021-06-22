package TwoPointer;

import java.util.HashMap;
import java.util.HashSet;

public class Two_Sum1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; map.put(nums[i],i++)) {
            if (map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]),i};
            }
        }
        throw new IllegalArgumentException("No result");
    }

    // we could also use the two pointer to solve this problem by sorting first;

}




