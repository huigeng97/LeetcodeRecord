package Sort;

import java.util.Arrays;

public class Minimum_Number_of_Arrows_to_Burst_Balloons452 {


    public int findMinArrowShots(int[][] points) {

        // (a,b) -> a[0] - b[0]  can't handle the overflow; only the non-negative number can use this one!

        Arrays.sort(points, (o1, o2) -> {
            // We can't simply use the o1[1] - o2[1] trick, as this will cause an
            // integer overflow for very large or small values.
            if (o1[1] == o2[1]) return 0;
            if (o1[1] < o2[1]) return -1;
            return 1;
        });
        int res = 1;
        int end = points[0][1];
        // If the next starting is smaller than the curr ending, then burst;
        // else the new ending update and add a more burst;
        for (int i= 1; i < points.length; i++) {
            if (points[i][0] > end) {
                end = points[i][1];
                res++;
            }
        }


        return res;

    }

    public static void main(String[] args) {

    }

}
