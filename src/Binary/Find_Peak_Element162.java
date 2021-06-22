package Binary;

// This is a very good question for understanding binary search,
// The binary search is used to find the first element that change a trend
// For example find the max number of a rotation sorted array (right go down. left go up )
// For a value in a sorted array (left is small than target, right is larger than target)
// Once there is a trend change, we coud always use the binary search to find the target,

// Open section is better than close section when we need to compare two sides.
// The open section can deal with the corner case well!

// Binary search is a special case for the divided and concur.
public class Find_Peak_Element162 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
