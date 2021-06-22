package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum39 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, 0, target, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] candidates, int start, int target, ArrayList<Integer> temp) {
        if (target<0 || start == candidates.length) return;
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        backtrack(candidates,start+1,target,temp);
        temp.add(candidates[start]);
        backtrack(candidates,start,target-candidates[start],temp);
        temp.remove(temp.size()-1);
    }

    public static void main(String[] args) {
        CombinationSum39 test = new CombinationSum39();
        test.combinationSum(new int[]{2, 3, 7},7);
    }

}
