package binary;

public class SearchInsertPostion35 {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

     while (start + 1 < end) {
        int mid = (start + end) / 2;

            // TODO： Do some comparison thing
        if (nums[mid] == target) {
            return mid;
        } else if  (nums[mid] < target) {
            start = mid;
        } else {
            end = mid;
        }


     }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        // the solution is either start or end;
         return -1;
    }
}
