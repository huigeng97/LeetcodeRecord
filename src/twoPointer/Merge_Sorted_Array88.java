package twoPointer;


// Seems an easy questions but only look from the right perspective;
// If we start from the left to the right, we have to use O(M) memory to achieve the O(M+N) runtime;
// However, if we change from right to left, we could do O(1) in place runtime;

public class Merge_Sorted_Array88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1;
        int p2 = n-1;
        int p = nums1.length-1;
        while (p >= 0) {
            if (p2 < 0 || (p1 >= 0 && nums1[p1] > nums2[p2])) {
                nums1[p] = nums1[p1];
                p--;
                p1--;
            } else {
                nums1[p--] = nums2[p2--];
            }
        }
    }

}
