package Sort;

public class Sort_Colors75 {
    // count sort
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int i=0; i<nums.length; i++) {
            count[nums[i]] += 1;
        }
        int color = 0;
        for (int i=0; i<nums.length; i++) {
            while (count[color] <= 0) {
                color++;
            }
            nums[i] = color;
            count[color]--;
        }
    }

    // use the in place sort, with two pointer;
    //It is actually a dual-pivot partitioning sub-routine of quick sort algorithm. Following is a well organized java code.
    // pivot is 1;

    // If the leftmost one is 0, lo++;
    // If the rightmost one is 2, hi--;
    // If it is 1, do nothing;

     //        Neat Code, I'm using your code to write more analysis:
     //       [0, lo) is the region for smaller than pivot;
     //       [lo, hi] is the region equals to pivot part;
     //       (hi, nums.length - 1] is the region for larger than pivot;
    public static void sortColors2(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int i = 0;
        while (i <= hi) {
            if (nums[i] == 1) i++;
            else if (nums[i] == 0) {
                swap(nums, i, lo);
                i++;
                lo++;
            } else {
                swap(nums, i , hi);
                hi--; // the reason this part doesn't have i++ is that after this swapping, we still can't make sure this one
                // is smaller or equals to the pivot; and all the numbers in the left of the i should be smaller or equal to pivot;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {

        sortColors2(new int[]{0,1,2,0,1,2,1,2,0,0});
    }
}
