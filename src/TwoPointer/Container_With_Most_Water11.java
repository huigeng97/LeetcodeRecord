package TwoPointer;

public class Container_With_Most_Water11 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length-1;
        int res = 0;
        while (l < r) {
            res = Math.max(Math.min(height[l],height[r])*(r-l),res);
            // Why ? this is a good question;
            // If I can't understand why we should compare the r and l instead of the r-1 and l+1,
            // I don't understand the two pointers problem;
            // The idea of the two pointers is to find the boundary; and keep the boudary optimal (greedy problem);
            // Think the extra condition, if there a two pillar with 100 heights and others just small, we should keep
            // the boundary at the 100 instead of others;
            // then we could always find the largest boundary condition;
            if (height[l] > height[r]) {
                r--;
            } else l++;
        }
        return res;
    }
}
