package Sort;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Car_Pooling1094 {


    public boolean carPooling(int[][] trips, int capacity) {
        int[][] starts = new int[trips.length][2];
        int[][] ends = new int[trips.length][2];
        for(int i=0; i<trips.length; i++) {
            starts[i][0] = trips[i][1];
            starts[i][1] = trips[i][0];
            ends[i][0] = trips[i][2];
            ends[i][1] = trips[i][0];
        }
        Arrays.sort(starts, (a,b) -> a[0] - b[0]);
        Arrays.sort(ends, (a,b) -> a[0] - b[0]);
        int entPtr = 0;
        capacity -= starts[0][1];
        if (capacity < 0) return false;
        // Use two pointers to go through the whole list;
        // If the start before the ending time, capacity  going down,
        // else capcity going up, end into a new one, until ending time larger than starting time;
        for (int i = 1; i < starts.length; i++) {
            while(starts[i][0] >= ends[entPtr][0]) {
                capacity += ends[entPtr][1];
                entPtr++;
            }
            capacity -= starts[i][1];
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }

    // The previous method could be update as the Treemap to avoid the double array;
    // The basic idea is the same as the latter one; just sorting the ordering of the timestamp of getting on and getting down
    public boolean carPooling1(int[][] trips, int capacity) {
        Map<Integer, Integer> timestamp = new TreeMap<>();
        for (int[] trip : trips) {
            int startPassenger = timestamp.getOrDefault(trip[1], 0) + trip[0];
            timestamp.put(trip[1], startPassenger);

            int endPassenger = timestamp.getOrDefault(trip[2], 0) - trip[0];
            timestamp.put(trip[2], endPassenger);
        }
        int usedCapacity = 0;
        for (int passengerChange : timestamp.values()) {
            usedCapacity += passengerChange;
            if (usedCapacity > capacity) {
                return false;
            }
        }
        return true;
    }

    // because of the constraints of the trips is between the 0 and 1000;
    // we could use an array to memorize all the record and then onetime passing the array to see if the capcity satisfies.

    public boolean carPooling2(int[][] trips, int capacity) {
        int[] record = new int[1001];
        for (int[] trip : trips) {
            record[trip[1]] += trip[0];
            record[trip[2]] -= trip[0];
        }
        int count = 0;
        for (int num : record) {
            count += num;
            if (count > capacity) return  false;
        }
        return true;
    }
}
