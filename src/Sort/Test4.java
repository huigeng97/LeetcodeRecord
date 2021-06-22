package Sort;

public class Test4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) return findMedianSortedArrays(nums2,nums1);
        // nums1 will be longer than nums2;

        // first is to find the pivot point; use the binary search to find one in the nums1;
        // parX and parY will be the last one of the partition;
        // total number
        int x = nums1.length;
        int y = nums2.length;
        int lo = 0;
        int hi = x;
        if (y == 0) return (x % 2 == 0) ? (nums1[x/2] + nums1[x/2-1]) / 2.0 : nums1[x/2];
        while (lo <= hi) {
            // parX and parY at the right side of the partition;
            int parX = (lo + hi) / 2;
            int parY = (x+y+1) / 2 - parX;

            // if the total number is even, the return number is the median of smaller one of the right size and the larger one of the left size;
            // if the total number is odd , return number is the larger one of the left side;

            int leftX = (parX == 0) ? Integer.MIN_VALUE : nums1[parX-1];
            int leftY = (parY == 0) ? Integer.MIN_VALUE : nums2[parY-1];
            int left = Math.max(leftX,leftY);

            int rightX = (parX == nums1.length) ? Integer.MAX_VALUE : nums1[parX];
            int rightY = (parY == nums2.length) ? Integer.MAX_VALUE : nums2[parY];
            int right = Math.min(rightX,rightY);

            if (left <= right) {
                /// return something;
                if ((x + y) % 2 == 0) return (left + right) / 2.0;
                else return left;
            } else {
                if (nums2[parY-1] < nums1[parX]) hi = parX - 1;
                else lo = parX + 1;
            }
        }
        throw  new IllegalArgumentException();
    }

}
