package Sort;

public class Median_of_Two_Sorted_Arrays4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

//        int m = nums1.length;
//        int n = nums2.length;
//        if (m < n) {
//            return findMedianSortedArrays(nums2, nums1);
//        }
//
//
//        // 起点和终点必须在合理区间内，不能四处游走；
//        int left = (m + n) / 2 - n;
//        int right = (m + n) / 2;

        // 处理短的串，而非长的串
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = m;

        while (left <= right) {
            int xPar = (left + right) / 2;
            int yPar = (m + n) / 2 - xPar;
            int xLeft = (xPar <= 0) ? Integer.MIN_VALUE : nums1[xPar - 1];
            int xRight = (xPar >= m) ? Integer.MAX_VALUE : nums1[xPar];

            int yLeft = (yPar <= 0) ? Integer.MIN_VALUE : nums2[yPar - 1];
            int yRight = (yPar >= n) ? Integer.MAX_VALUE : nums2[yPar];

            int Left = Math.max(xLeft, yLeft);
            int Right = Math.min(xRight, yRight);

            if (Left <= Right) {
                return (m + n) % 2 == 1 ? Right : (double) (Right + Left) / 2;
            } else {
                if (xLeft > yRight) {
                    right = xPar - 1;
                } else {
                    left = xPar + 1;
                }
            }
        }
        return -1;
    }
}
