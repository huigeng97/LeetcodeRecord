package DP;

import java.util.Arrays;
import java.util.TreeMap;

// This is a classical DP problem but with the binary search to speed up;
public class Maximum_Profit_in_Job_Scheduling1235 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // sort the job by the finishing time;
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, (a,b) -> a[1] - b[1]);
        int[] maxProfit = new int[jobs.length];
        // use the dp to calculate the maxProfit when the job i is finished;
        // dp[i] = max(dp[i-1], profit[i] + dp[k]) where k is the largest number that job[k] is ahead of the job[i];
        // use the binary search to search the k;
        for (int i = 0; i < maxProfit.length; i++) {
            int k = search(jobs, i);
            if (k == -1) {
                maxProfit[i] = Math.max((i-1) >= 0 ? maxProfit[i-1] : 0, jobs[i][2]);
            } else {
                maxProfit[i] = Math.max(maxProfit[i-1], maxProfit[k] + jobs[i][2]);
            }
        }
        return maxProfit[maxProfit.length-1];
    }


    // use the binary search to search the largest k that endTime is equal to or smaller than index i ;
    private int search(int[][] jobs, int index) {
        int lo = 0, hi = index;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (mid == index) return - 1;
            if (jobs[mid][1] <= jobs[index][0] && jobs[mid+1][1] > jobs[index][0] ) {
                return mid;
            }
            if (jobs[mid][1] > jobs[index][0]) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    // There is another way without the binary search by using the treeMap:
    // Use the treeMap to store the ending time and dp value, ending time is the key so we could compare
    // the starting time with the biggest ending time is smaller than cur starting time;
    // If we don't accept the value, we could not use it.
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b)->a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if (cur > dp.lastEntry().getValue())
                dp.put(job[1], cur);
        }
        return dp.lastEntry().getValue();
    }


}
