package binary;

import java.util.*;

public class Furthest_Building_You_Can_Reach1642 {
    // This method is using the binary search but without the memorization of the sorted climb;
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int lo = 0;
        int hi = heights.length - 1;
        List<int[]> sortedClimb = new ArrayList<>();
        for (int i = 1; i < heights.length; i++){
            int climb = heights[i] - heights[i-1];
            if (climb > 0) {
                sortedClimb.add(new int[]{climb, i});
            }
        }
        Collections.sort(sortedClimb, (a,b) -> a[0]-b[0]);

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (!isReach(sortedClimb,bricks,ladders,mid)) {
                hi = mid - 1;
            } else if (isReach(sortedClimb,bricks,ladders,mid) && (mid == hi) ? true : !isReach(sortedClimb,bricks,ladders,mid+1)) {
                return mid;
            } else {
                lo = mid + 1;
            }
        }
        throw  new IllegalArgumentException();
    }


    private boolean isReach(List<int[]> sortedClimb, int bricks, int ladders, int target) {

        for (int[] climb : sortedClimb) {
            if (climb[1]>target) continue;
            if (bricks >= climb[0]) {
                bricks -= climb[0];
                continue;
            } else if (ladders>0) {
                ladders -= 1;
                continue;
            } else return false;
        }
        return true;
    }
}
