package slidingWin;

import java.util.PriorityQueue;

public class Find_Median_from_Data_Stream295 {


    class MedianFinder {

        /** initialize your data structure here. */
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;
        int size;
        public MedianFinder() {
            left = new PriorityQueue<>((a, b) -> b - a);
            right = new PriorityQueue<>();
            size = 0;
        }

        public void addNum(int num) {
            // add the number into one of the left and right;
            // the left is one larger or equal to the right;
            // if the size is odd, add the number into the right; or add it to the left and then move one from left to right;
            // if the size is even, add the number into the left; or add it to the right and then move one form the right to left;

            // revision: use a self balancing method, if we use the PQ, we offer the left first and then pop the left into the right;
            // then the two heaps are always balancing;
            left.offer(num);
            right.offer(left.peek());
            left.poll();
            if (left.size() < right.size()) {
                left.offer(right.peek());
                right.poll();
            }
        }

        public double findMedian() {
            return (left.size() == right.size()) ? (left.peek() + right.peek()) / 2.0 : left.peek();
        }
    }

    // several method could be used for this problem like the TreeMap (multiset);
    // segment dividing method (Bucket sorting)



}
