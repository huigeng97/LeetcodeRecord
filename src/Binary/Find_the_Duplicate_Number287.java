package Binary;
// This is a very interesting problem
// Convert the array to linkedlist
public class Find_the_Duplicate_Number287 {
    // use the Floyd algorithm to solve it
    // The most important thing is to find which is pointer and which is value;
    // We need to compare the pointer, not the value;
    // nums[0] is a pointer to the second node;
    // 0 is the pointer to the first node;
    public int findDuplicate(int[] nums) {
        if (nums.length <= 1) return -1;
        int fast = 0;
        int slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (nums[fast] == nums[slow]) {
                break;
            }
        }
        slow = 0;
        while (nums[fast] != nums[slow]) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return nums[fast];
    }
}
