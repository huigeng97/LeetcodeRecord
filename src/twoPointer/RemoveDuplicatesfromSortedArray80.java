package twoPointer;

public class RemoveDuplicatesfromSortedArray80 {

    public int removeDuplicates(int[] nums) {

//        if (nums.length <= 2) {
//            return nums.length;
//        }
//        int first = (nums[0] == nums[1]) ? 0 : 1;
//        int sSlow = 2;
//        for (int i = 2; i < nums.length; i++) {
//            if (nums[i] != nums[i - 1]) {
//                first = i;
//            }
//            if (nums[i] != nums[first] || i - first < 2) {
//                nums[sSlow++] = nums[i];
//            }
//        }
//        return sSlow;

       // this solution only focus on the new array, only if the new array without three same item,
       // we are confident to add the current item into the new array;

        int i=1;
        for (int j=2; j<nums.length; j++) {
            if (nums[j]==nums[i] && nums[j]==nums[i-1]) {
                continue;
            } else {
                i++;
                nums[i]=nums[j];
            }
        }
        return i + 1;
    }
}
