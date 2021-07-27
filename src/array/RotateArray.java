package array;

import java.util.Arrays;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        // for (int i = 0; i < k; i++) {
        //     int temp = nums[nums.length - 1];
        //     for (int j = nums.length - 1; j >= 1; j--) {
        //         nums[j] = nums[j - 1];
        //     }
        //     nums[0] = temp;
        // }

        int n = nums.length;
        int[] temp = new int[n];
        System.arraycopy(nums, 0, temp, 0, n);
        for (int i = 0; i < n; i++) {
            nums[i] = temp[(i - (k % n) + n) % n];
        }
    }

    // There are two other methods :
    // Cyclic Replacment and reverse Method:

    public void rotate2(int[] nums, int k) {

        return;
    }

    public void rotate23(int[] nums, int k) {

        return;
    }
}
