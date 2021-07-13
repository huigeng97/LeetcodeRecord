package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Permutations46 {

    List<List<Integer>> output = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {

        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums) {
            nums_lst.add(num);
        }

        int length = nums.length;
        backtrack(length,nums_lst,0);
        return output;
    }

    private void backtrack(int length, ArrayList<Integer> nums_lst, int index) {
        if (index == length) {
            output.add(new ArrayList<Integer>(nums_lst));
            return;
        }

        for (int i = index; i < length; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums_lst, index, i);
            // use next integers to complete the permutations
            backtrack(length, nums_lst, index + 1);
            // backtrack
            Collections.swap(nums_lst, index, i);
        }
    }
}
