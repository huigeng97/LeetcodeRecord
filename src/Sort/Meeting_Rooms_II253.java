package Sort;

import java.util.Arrays;
import java.util.PriorityQueue;

// This question needs to think carefully, is to find the meeting room that without the overlap
// A contradictory question with the the merge interval

// please note that this question is also a two pointer question
// one pointer starting from the start;
// another pointer starting from the end;
// two through and it will show how many rooms require;


public class Meeting_Rooms_II253 {
    public int minMeetingRooms(int[][] intervals) {
        //First we need to sort the intervals by starting point;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Use a min-heap to allocate the meeting room by its ending time;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(intervals[0][1]);

        // If current starting time is smaller than the smallest ending time // add new room;
        // else the room is free, pop it and add a new one;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }

    public int minMeetingRooms2(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int end = 0;
        int res = 1;
        // Now there is one room required;
        // If the new starting time is smaller than the curr ending time, one more room required, but we don't know
        // when it ends, so the end pointer doesn't change;
        // Else we don't need a new room, but we know that the currect starting time is larger or equal than the ending time
        // we need to update the ending time to next one;
        for (int i=1 ;i < starts.length; i++) {
            if (starts[i] < ends[end]) {
                res += 1;
            } else {
                end ++;
            }
        }
        return res;
    }
}


























