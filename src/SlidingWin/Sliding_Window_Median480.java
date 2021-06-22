package SlidingWin;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


// This problem is the follow up questions on 295. Find Median from Data Stream;
// Do 295 First and then come to do this problem;
public class Sliding_Window_Median480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        // use the PQ to store the window;
        double[] res = new double[nums.length-k+1];
        PriorityQueue<Integer> small = new PriorityQueue();
        PriorityQueue<Integer> large = new PriorityQueue();
        return res;
    }
}
