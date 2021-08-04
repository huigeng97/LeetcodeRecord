package A.DailyPractice;


import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.IntStream;

public class Review0729 {
    public int maximumGap(int[] a) {

        // build the bucket;
        // find the max and min of each bucket;
        // the difference between the buckets

        // 检验每个bucket的gap
        if (a== null || a.length <= 2) {
            return 0;
        }

        int[] bMin = new int[a.length];
        Arrays.fill(bMin, Integer.MIN_VALUE);
        int[] bMax = new int[a.length];
        Arrays.fill(bMax, Integer.MAX_VALUE);

        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
            min = Math.min(min, a[i]);
        }

        int gap = (int) Math.ceil((max - min) / (a.length - 1));

        for (int i = 0; i < a.length; i++) {
            if (a[i] == max) {
                continue;
            }
            int id = (a[i] - min) / gap;
            bMax[id] = Math.max(bMax[id], a[i]);
            bMin[id] = Math.min(bMin[id], a[i]);
        }

        int maxGap = 0;
        int previous = min;
        for (int i = 0; i < a.length - 1; i++) {
            if (bMin[i] == Integer.MAX_VALUE && bMax[i] == Integer.MIN_VALUE) {
                continue;
            }

            maxGap = Math.max(maxGap, bMin[i] - previous);

            previous = bMax[i];
        }
        maxGap = Math.max(maxGap, max - previous);
        return maxGap;

    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // first find the position of the x;
        if (k == 0) {
            return new ArrayList<>();
        }
        int pos = searchInsert(arr, x);
        List<Integer> res = new ArrayList<>();
        if (pos < arr.length) {
            res.add(arr[pos]);
            k--;
        }
        int lo = 1;
        int hi = 1;
        while (k > 0) {
            Integer small = Integer.MAX_VALUE;
            Integer large = Integer.MAX_VALUE;
            if (pos - lo >= 0) {
                small = x - arr[pos - lo];
            }
            if (pos + hi < arr.length) {
                large = x - arr[pos + hi];
            }
            if (small <= large) {
                res.add(arr[pos - lo]);
                lo++;
            } else {
                res.add(arr[pos + hi]);
                hi++;
            }
            k--;
        }
        Collections.sort(res);
        return res;


        // then find the next k element;
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length;

        while (start + 1 < end) {
            int mid = (start + end) / 2;

            // TODO： Do some comparison thing
            if  (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] >= target) {
            return start;
        } else {
            return end;
        }
    }

    public boolean canPartitionKSubsets(int[] a, int k) {
        int sum = IntStream.of(a).sum();
        return k != 0 && sum % k == 0 &&
                canPartition(0, a, new boolean[a.length], k, 0, sum / k);
    }

    boolean canPartition(int start, int[] a, boolean[] seen, int k, int sum, int target) {
        if (k == 1) return true;
        if (sum == target)
            return canPartition(0, a, seen, k - 1, 0, target);
        for (int i = start; i < a.length; i++)
            if (!seen[i]) {
                seen[i] = true;
                if (canPartition(i + 1, a, seen, k, sum + a[i], target))
                    return true;
                seen[i] = false;
            }
        return false;
    }


    public int findKMiss(int[] nums, int k) {

        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (hi - lo) / 2;
            int temp = nums[mid] - nums[0] - mid;
            if (temp == k) {
                System.out.println("inside");
                return  nums[mid - 1] + (k - mid + 1);
            }
            else if (temp > k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println("outside");
        return nums[hi + 1] + (k - hi - 1);
    }



  public static void main(String[] args) {
    //
//      String[] s = "ss1".split("s", -1);
//      System.out.println("size : " + s.length);
//      for (String str : s) {
//          System.out.println(str);
//      }
      Review0729 test = new Review0729();
//      System.out.println(test.canPartitionKSubsets(new int[]{-4,-3,-2,-3,-5,-2,-1}, 4));




  }


    @Test
    public void testCode() {
        Review0729 test = new Review0729();
        int[] arr = new int[]{2,4,7,8,9,15};
        int[] result = new int[]{0,3,5,6,10,11,12,13,14};
        for (int i = 1; i <= 5; i++) {
            assertEquals(test.findKMiss(arr,i), result[i]);
        }
    }
}
