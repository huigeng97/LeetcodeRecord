package DP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class LongestConsecutiveSequence128 {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer num : nums) {
            set.add(num);
        }

        int temp = set.first();
        int curr = 1, max = 1;
        for (Integer num : set) {
            if (num == temp + 1) {
                curr++;
                max = Math.max(max,curr);
            } else {
                curr = 0;
            }
            temp = num;
        }
        return max;
    }

    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {return 0;}
        Arrays.sort(nums);
        int temp = nums[0];
        int curr = 1, max = 1;
        for (Integer num : nums) {
            if (num == temp) continue;
            if (num == temp + 1) {
                curr++;
                max = Math.max(max,curr);
            } else {
                curr = 1;
            }
            temp = num;
        }
        return max;
    }

    // The fastest method is to use the HashSet;.....
    // Then it could be solved by query if set contains (curr - 1);

    // 巧妙之处在于只会遍历一个连续数组的最小值，对于其他值都continue;

    // This question could also be solved by union Find;

    public int longestConsecutive3(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
