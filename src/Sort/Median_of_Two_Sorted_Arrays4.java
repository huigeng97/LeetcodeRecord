package Sort;

public class Median_of_Two_Sorted_Arrays4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // first to partition the array into two equal parts;
        // always partition the short array, because it is faster;
        // This is very important to conver the T(n,m) from log(max(m,n)) into log(min(m,n))
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length;
        int y = nums2.length;
        int lo = 0;
        int hi = x;
        // corner case? edge case;
        if (y == 0) return (x % 2 == 0) ? (nums1[x/2] + nums1[x/2-1]) / 2.0 : nums1[x/2];
        while(lo <= hi){
            // left side equals to or larger than 1 of the half of the array;
            // partitionX and Y is the right side of the partition;
            // the length of the left side is (x+y+1)/2;
            int partitionX = (lo + hi) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            // then compare the leftX rightX, left Y right Y;
            int leftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX-1];
            int leftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY-1];

            int rightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            int rightY = (partitionY == y) ? Integer.MAX_VALUE : nums1[partitionY];
            // if the median, calculate the median value;
            if (leftX <= rightY && leftY <= rightX) {
                if ((x+y)%2 == 0) {
                    return (double) (Math.max(leftX,leftY) + Math.min(rightX,rightY)) / 2.0;
                } else {
                    return Math.max(leftX,leftY);
                }
            }

            if (leftX > rightY) {
                hi = partitionX - 1;
            } else {
                lo = partitionX + 1;
            }

            // if is not the median, repartition the arrays using the binary search;
        }
        throw  new IllegalArgumentException();
    }
}
