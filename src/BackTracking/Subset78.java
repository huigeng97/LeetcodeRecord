package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Subset78 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums,0,new ArrayList<>());
        res.add(new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, int i, ArrayList<Integer> temp) {
        if (i < nums.length) {
            backtrack(nums, i+1, temp);
            temp.add(nums[i]);
            res.add(new ArrayList<>(temp));
            backtrack(nums,i+1,temp);
            temp.remove(temp.size()-1);
        }
    }
}
