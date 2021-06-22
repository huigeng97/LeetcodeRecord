package Binary;

public class Find_First_and_Last_Position34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int lo = -1;
        int hi = -1;
        // first find the left side with closed section
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target && (mid == 0 || nums[mid-1] < target)) {
                lo = mid;
                break;
            } else if (nums[mid] >= target) { right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (lo == -1) return new int[]{-1,-1};
        // then find the right side with the closed section
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid+1] > target)) {
                hi = mid;
                break;
            } else if (nums[mid] > target) { right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{lo,hi};
    }


    // This is an interesting implementation with only one binary search implementation.
    public int[] searchRange2(int[] A, int target) {
        int start = firstGreaterEqual(A, target);
        if (start == A.length || A[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
    }

    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    //actually this is the same as lower_bound in C++ STL.
    private static int firstGreaterEqual(int[] A, int target) {
        int low = 0, high = A.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            //low <= mid < high
            if (A[mid] >= target && (mid == 0 || A[mid-1] < target)) return mid;
            if (A[mid] < target) {
                low = mid + 1;
            } else {
                //should not be mid-1 when A[mid]==target.
                //could be mid even if A[mid]>target because mid<high.
                high = mid;
            }
        }
        return low;
    }
}
