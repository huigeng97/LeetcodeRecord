package TwoPointer;


// This is a very classical two pointer question;
// Alternate search left and right until they meet in the middle;
// other method like DP and Stacks can be used too;

// This method could be easily transferred into the 3D case if you understand why we need to two pointers
// The core idea of the two pointer questions is always picking from the boundary until traverse all the points
// (in 2D case, it is two pointer, in 3D case, they are all the outside boundary points);
// I feel that two pointers actually the greedy algorithm;
public class Trapping_Rain_Water42 {
    public static int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        int res = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if ((height[left] >= left_max)) {left_max = height[left];} else {res += (left_max - height[left]);}
                ++left;
            } else {
                if (height[right] >= right_max) right_max = height[right] += (right_max - height[right]);
                else res += (right_max - height[right]);
                right --;
            }
        }
        return res;
    }

    // This Dp method is an intuitive mathetical result for this problem;
    // for each element, find the max height in the right and max height in the left;
    // the water in this element is the Min(maxleft , maxright) - height;
    public static int trap2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int[] leftMax = new int[heights.length]; leftMax[0] = heights[0];
        int[] rightMax = new int[heights.length]; rightMax[heights.length-1] = heights[heights.length-1];
        for (int i = 1; i < heights.length; i++) {
            leftMax[i-1] = Math.max(leftMax[i-1], heights.length);
        }
        for (int i = heights.length - 2; i >= 0; i--) {
            rightMax[i-1] = Math.max(rightMax[i+1], heights.length);
        }
        int res = 0;
        for (int i = 0; i < heights.length; i ++) {
            res += Math.min(rightMax[i],leftMax[i]) - heights[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
