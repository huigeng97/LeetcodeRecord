package binary;

public class Search_in_Rotated_Sorted_Array33 {
    // It is very important to write the comment!
    // Binary search needs to learn the section is open or close
    // Personally speaking, the close section is easier for me.
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println(mid);
            if (nums[mid] == target) return mid;
            // left side without rotation
            if (nums[mid] >= nums[left]) {
                // numbers in the left side;
                if (nums[mid] > target && nums[left] <= target) right = mid - 1;
                    // otherwise
                else left = mid + 1;
                // left side with rotation (i.e. right side without rotation)
            } else {
                // numbers in the right side;
                if (nums[right] >= target && nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }

    // This method is to find the rotation number first and then find the target number.
    // Twice binary searching.
    public int search2(int A[], int target) {
        int n = A.length;
        int lo=0,hi=n-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(A[mid]>A[hi]) lo=mid+1;
            else hi=mid;
        }
        // lo==hi is the index of the smallest value and also the number of places rotated.
        int rot=lo;
        lo=0;hi=n-1;
        // The usual binary search and accounting for rotation.
        while(lo<=hi){
            int mid=(lo+hi)/2;
            int realmid=(mid+rot)%n;
            if(A[realmid]==target)return realmid;
            if(A[realmid]<target)lo=mid+1;
            else hi=mid-1;
        }
        return -1;
    }

    public int search3(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // first find the pivot number (the max number), then either search the left part or right part
        int pivot = right;
        // there is a rotation;
        int res = -1;
        if (nums[left] > nums[right] ) {
            // double close section;
            while (left <= right) {
                int mid = (right + left) / 2;
                if (nums[mid] > nums[mid+1]) {
                    pivot = mid;
                    continue;
                } else if (nums[mid] < nums[mid-1]) {
                    pivot = mid - 1;
                    continue;
                } else if (nums[mid] > nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            System.out.println("find the pivot index" + pivot);
            if (target < nums[0]) {
                res = binarySearch(nums, pivot + 1, nums.length-1, target);
            } else {
                res = binarySearch(nums, 0, pivot, target);
            }
        } else {
            res = binarySearch(nums, left, right, target);
        }
        return res;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return - 1;
    }
}
