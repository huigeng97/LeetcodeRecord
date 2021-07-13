package DP2;

import java.util.Arrays;
import java.util.TreeMap;

public class MaximumProfitinJobScheduling1235 {

    // use DP to solve it;
    // the current profit for schedule ending ith job, is the dp[i] = dp[i-1] , dp[k] + profit[i], where kth ending is
    // smaller than endTime[k];

    static class Job {
        int profit;
        int start;
        int end;

        public Job(int profit, int start, int end) {
            this.profit = profit;
            this.start = start;
            this.end = end;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(profit[i], startTime[i], endTime[i]);
        }
        Arrays.sort(jobs, (a,b) -> a.end - b.end);
        int[] dp = new int[jobs.length + 1];
        int k = 0;
        for (int i = 0; i < jobs.length; i++) {

            // we could use the TreeMap to do the same thing of this part;
            // the function is to get TreeMap dp.floorEntry(jobs[i].start);

            while (jobs[k].end > jobs[i].start && k > 0) {
                k--;
            }
            while (jobs[k].end <= jobs[i].start) {
                k++;
            }
            // System.out.println("current jobs + " + jobs[i].start + " " + jobs[i].profit +  " " + jobs[i].end);
            if (k != 0) {
                // System.out.println("Last jobs + " + jobs[k - 1].start + " " +  jobs[k - 1].profit +  " " + jobs[k - 1].end);
            }
            dp[i + 1] = Math.max(dp[i], jobs[i].profit + dp[k]);
        }
        return dp[dp.length - 1];
    }

    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {
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
                {dp.put(job[1], cur);}
        }
        return dp.lastEntry().getValue();
    }
}
