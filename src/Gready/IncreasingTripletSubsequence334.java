package Gready;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Geng Hui
 */
public class IncreasingTripletSubsequence334 {
    public boolean increasingTriplet(int[] nums) {

        if (nums.length <= 3) {return false;}
        int left = 0;
        int right = 0;
        Deque<Integer> res = new ArrayDeque<>();
        res.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= res.peek()) {
                res.push(nums[i]);
                if (res.size() == 3) {
                    return true;
                }
            } else {
                while (!res.isEmpty() && nums[i] < res.peek()) {
                    res.pop();
                }
                res.push(nums[i]);
            }
        }
        return false;

    }
}
