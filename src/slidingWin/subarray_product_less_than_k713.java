package slidingWin;

public class subarray_product_less_than_k713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int left = 0; int right = 0;
        int prod = 1;
        if (k == 0) return 0;
        while (right < nums.length ) {
            prod *= nums[right];
            while (prod >= k && left <= right) {
                prod /= nums[left++];
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }
}
