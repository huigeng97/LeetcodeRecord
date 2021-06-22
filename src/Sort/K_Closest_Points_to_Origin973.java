package Sort;

import java.util.Arrays;
import java.util.PriorityQueue;

public class K_Closest_Points_to_Origin973 {

    // This method is very simple, just sort the points with the defined distance formula,
    // Then we could use the method Arrays.copyOfRange( array, start, end ) to find the solution;
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }


    // Now lets think about if we could find a better solution
    // With like one pass?
    // At least the solution should be better than (ologn) in some case;
    // One thing we could do is to use a PQ to store the top K points and always compare the furthest one with the curr one

    public int[][] kClosest2(int[][] points, int K) {
        // The distance is always no negative, so free to use -> b-a;
        // Otherwise use the one that :
        PriorityQueue<int[]> pq = new PriorityQueue<>((p2,p1) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > K) {
                pq.poll();
            }
            // still not familar with the pq usage, Just offer and poll is enough;

        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }
        return res;
    }
    //

    // This problem can also be solved by quick sort with binary search (K)!
    // We first need to find the pivot place and put all the points less than pivot at left, if pivot's index > k
    // go to the left part and repivot, otherwise goto the right part and repivot;
    // this question is a combination of the pivot(quick selection);
    // 函数的分离做的不好！
    // 主函数找K， helper函数quick selection;
    // 提前设计好才能流畅地写出来！
    public int[][] kClosest3(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = quickSort(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        // first we need to pivot the point; and put all the points smaller than the pivots in the left
        // and larger in the right;
        return Arrays.copyOfRange(points, 0, K);

    }

    private int quickSort(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l<r) {
            while ( l < r && compare(A[r] , pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return  l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }

    // Must pivot from one side and start from another side; then we could save lots of redundent step;
    // we first pivot at left and then start from the right to change the pivot to the first small one in the left;
    // then we replace the right small one with the left large one. Then we have two large one;
    // once we have gone through the whole list, where we stop is the duplicate key that we forget to replace with the pivot;
    public static int quickSort2(int[] A, int l, int r) {
        int pivot = A[l];
        while (l<r) {
            while ( l < r && A[r] - pivot >= 0) r--;
            A[l] = A[r];
            while (l < r && A[l] - pivot <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return  l;
    }

    public static void main(String[] args) {

        System.out.println(quickSort2(new int[]{10,5,7,2,12,4,5,9,71}, 0, 8));
    }
}
