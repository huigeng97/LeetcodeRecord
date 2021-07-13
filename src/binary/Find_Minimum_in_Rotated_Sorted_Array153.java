package binary;

public class Find_Minimum_in_Rotated_Sorted_Array153 {
    public int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length-1]) {
            return nums[0];
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[mid + 1]) return nums[mid+1];
            if (nums[mid-1] > nums[mid]) return nums[mid];
            // If the left is the sorted array;
            // then the mid is at the right;
            // if the right is the sorted array;
            // then the mid is at the left;
            if (nums[0] <= nums[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
