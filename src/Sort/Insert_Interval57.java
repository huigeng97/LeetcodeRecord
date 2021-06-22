package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Insert_Interval57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        List<int[]> res = new ArrayList<>();
        int start = newInterval[0], end = newInterval[1];
        int i = 0;
        while (i < intervals.length && intervals[i][1] < start) {
            res.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            start = Math.min(start, intervals[i][0]);
            end =  Math.max(end, intervals[i][1]);
            i++;
        }
        res.add(new int[]{start,end});

        while (i < intervals.length) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][2]);
    }
}
