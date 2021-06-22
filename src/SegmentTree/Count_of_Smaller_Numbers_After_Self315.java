package SegmentTree;

import java.util.*;

public class Count_of_Smaller_Numbers_After_Self315 {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        Mytree newTree = new Mytree();
        for (int i = nums.length - 1; i >= 0; i++) {
            int value = newTree.update(nums[i]);

            // insert the current number into the sorted curr list;
            // count the number less than current number;
            // add the number into the res;
        }
        Collections.reverse(result);
        return  result;
    }

    public class Mytree {
        treeNode root;

        private class treeNode{
            int value;
            int index;
            treeNode root;
            treeNode right;
            public treeNode(int value, int index) {
                this.index = index;
                this.value = value;
            }

        }

        public Mytree(int value) {
            root = new treeNode(value, 0);
        }

        public Mytree() {
        }

        public int update(int num) {
            return 0;
        }
    }

    // This is a method of the segment tree;
    // Stop at the node index = 1;
    public List<Integer> countSmaller2(int[] nums) {
        int offset = 10000; // offset negative to non-negative
        int size = 2 * 10000 + 1; // total possible values in nums
        int[] tree = new int[size * 2];
        List<Integer> result = new ArrayList<Integer>();

        for (int i = nums.length -1; i >= 0; i++) {
            int count = query1(0,nums[i]+offset, tree, size);
            result.add(count);
            update(1,nums[i]+offset,tree,size);
        }
        Collections.reverse(result);
        return result;
    }

    // update the value of pos with plus diff; and then update each of the node contains the pos;
    //
    private void update(int diff, int pos, int[] tree, int size) {

        int temp = size + pos;
        // stop at temp = 1;
        while (temp>0) {
            tree[temp] += diff;
            temp = temp / 2;
        }
    }

    // query the interval sum between [start,end);
    // two method: iterative or recursive
    // the recursive is top down;
    // the iterative is bottom up;
    // 大概懂了，但做起来还是很难啊；
    private int query1(int start, int end, int[] tree, int size) {
        // recursive one
        // result should be equal to [start/mid) + [mid/end);
        return 0;

    }


}
