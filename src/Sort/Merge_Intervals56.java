package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// First sort question, good start;
// The time complexity is O(n) + O(nlogn);
// constant space complexity;
public class Merge_Intervals56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(intervals[i][1],end);
            } else {
                res.add(new int[]{start,end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        res.add(new int[]{start,end});
//        int[][] ret = new int[res.size()][2];
//        for (int i = 0; i < res.size(); i++) {
//            ret[i] = res.get(i);
//        }
//        return ret;
        return res.toArray(new int[res.size()][]);
    }

}
