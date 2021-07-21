package A.DailyPractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WeeklyContest0717 {
    public int canBeTypedWords(String text, String brokenLetters) {

        int[] broken = new int[26];
        for (char ch : brokenLetters.toCharArray()) {
            broken[ch - 'a']++;
        }

        int count = 0;
        for (String word : text.split(" ")) {
            boolean isValid = true;
            for (char ch : word.toCharArray()) {
                if (broken[ch - 'a'] != 0) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                count++;
            }
        }
        return count;
        // Time 5 min;
    }

    public int addRungs(int[] rungs, int dist) {

        int start = 0;
        int count = 0;
        for (int rung : rungs) {
            start = Math.min(rung, start + dist);
            if (start < rung) {
                count += (rung - start - 1) / dist + 1;
            }
            start = rung;
        }
        return  count;

        //  Time 10 mins, 3 wrong submission;
    }

    public long maxPoints(int[][] points) {

        long[][] dp = new long[points.length][points[0].length];
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = points[0][j];
        }
        for (int i = 1; i < points.length; i++) {
            dp[i] = calculate(dp[i - 1], points[i]);
        }
        long max = 0;
        for (int j = 0; j < dp[0].length; j++) {
            max = Math.max(dp[dp.length - 1][j], max);
        }
        return max;

        // Time 45 min;
    }

    private long[] calculate(long[] oldPoints, int[] points) {
        long[] newPoints = new long[oldPoints.length];
        int upperIdx = 0;
        long upperReal = 0;
        int maxIdx = 0;
        for (int j = 0; j < newPoints.length; j++) {
            upperIdx = maxIdx;
            upperReal = oldPoints[upperIdx] - Math.abs((upperIdx - j));
            while (upperIdx < newPoints.length) {
                long curr = oldPoints[upperIdx] - Math.abs((upperIdx - j));
                if (curr > upperReal) {
                    maxIdx = upperIdx;
                    upperReal = curr;
                }
                upperIdx++;
            }
            newPoints[j] = upperReal + points[j];
        }
        return newPoints;

    }


    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        Map<Integer, HashMap> map = new HashMap<>();
        int[] res = new int[queries.length];
        // difference between a xor b = a ^ b;
        for (int i = 0; i < queries.length; i++) {

            int start = queries[i][0];

            int max = 0;
            int idx = start;
            int query = queries[i][1];
            if (!map.containsKey(start)) {
                HashMap<Integer,Integer> par = new HashMap<>();
                while (start != -1) {
                    int temp = parents[start] ^ query;
                    if (max < temp) {
                        max = temp;
                        idx = start;
                    }
                    par.put(start, max);
                    start = parents[start];
                }
                map.put(start, par);
            } else {
                
            }

            res[i] = max;
        }
        return res;
    }

  public static void main(String[] args) {
    //
      WeeklyContest0717 test = new WeeklyContest0717();
      test.maxPoints(new int[][]{{1,2,3},{1,5,1},{3,1,1}});
      System.out.println(5 ^ 2);
  }
}
