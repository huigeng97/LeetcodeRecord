package A.DailyPractice;

import javafx.util.Pair;
import org.junit.jupiter.params.shadow.com.univocity.parsers.tsv.TsvRoutines;

import java.util.*;

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


    public int getLucky(String s, int k) {

        String temp = convert(s);
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num += s.charAt(i);
        }

        for (int i = 0; i < k - 1; i++) {
            int start = num;
            while (start != 0) {
                num += start % 10;
                start = start / 10;
            }
        }
        return num;
    }

    private String convert(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) - 'a');
        }
        return new String(sb);
    }


    public String maximumNumber(String num, int[] change) {
        if (num.length() == 0) {
            return "";
        }
        boolean[] check = new boolean[num.length()];
        StringBuilder sb = new StringBuilder();
        int curr = num.charAt(0) - '0';
        boolean stopped = false;
        if (curr < change[curr]) {
            check[0] = true;
            curr = change[curr];
        }
        sb.append(curr);
        for (int i = 1; i < num.length(); i++) {
            curr = num.charAt(i) - '0';
            if (!stopped) {
                // this way it is not fully used; so we could start to manipulate;
                // if last one is false; we haven't started;
                // else we already started;
                if (check[i - 1] == false) {
                    if (curr < change[curr]) {
                        check[i] = true;
                        curr = change[curr];
                    }
                } else {
                    if (curr <= change[curr]) {
                        check[i] = true;
                        curr = change[curr];
                    } else {
                        stopped = true;
                    }
                }
            }
            sb.append(curr);
        }
        return new String(sb);
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {

        class pair {
            int student;
            int mentor;

            public pair(int student, int mentor, int distance) {
                this.student = student;
                this.mentor = mentor;
                this.distance = distance;
            }

            int distance;

        }
        // it is possible to use a PQ to update the top m edges
        int count = 0;
        int nums = students.length;
        int sum = 0;
        PriorityQueue<pair> pq = new PriorityQueue<pair>((a,b) -> b.distance - a.distance);
        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < mentors.length; j++) {
                pq.offer(new pair(i,j, distance(students[i],mentors[j])));
            }
        }
        HashSet<Integer> pairStudent = new HashSet<>();
        HashSet<Integer> pairMentor = new HashSet<>();
        while (count < nums) {
            pair curr = pq.poll();
            if (pairMentor.contains(curr.mentor) && pairStudent.contains(curr.student)) {
                sum += curr.distance;
                pairMentor.add(curr.mentor);
                pairStudent.add(curr.student);
                count++;
            }
        }
        return sum;
    }

    private int distance(int[] student, int[] mentor) {
        int ans = 0;
        for (int i = 0; i < student.length; i++) {
            ans  += student[i] == mentor[i] ? 1 : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
    //
      WeeklyContest0717 test = new WeeklyContest0717();
      test.maxPoints(new int[][]{{1,2,3},{1,5,1},{3,1,1}});
      System.out.println(5 ^ 2);
  }
}

