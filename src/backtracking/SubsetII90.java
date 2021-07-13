package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetII90 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums,0,new ArrayList<>());
        res.add(new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, int i, ArrayList<Integer> temp) {
        if (i < nums.length) {
            temp.add(nums[i]);
            res.add(new ArrayList<>(temp));
            backtrack(nums,i+1,temp);
            temp.remove(temp.size()-1);

            // 写代码要注意流畅性和易读性；所谓的边界条件不过是为了满足不报错；
            // 从i = 0 开始， nums[i+1]要在边界内；
            while (i+1 < nums.length && nums[i+1] == nums[i]) {
                i++;
            }
            backtrack(nums, i+1, temp);
        }
    }
}
