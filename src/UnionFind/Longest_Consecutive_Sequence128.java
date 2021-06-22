package UnionFind;

import java.util.HashSet;
import java.util.Set;


/// This question appears in the union find section, but many methods could solve it;
// Such as sorting
// Such as the hashset method;
public class Longest_Consecutive_Sequence128 {

    class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int A) {
            if (parent[A] != A) parent[A] = find(parent[A]);
            return parent[A];
        }

        public int union(int A, int B) {
            int rootA = find(A);
            int rootB = find(B);
            if (rootA != rootB) {
                int sizeTotal = size[rootA] + size[rootB];
                if (size[rootA] < size[rootB]) {
                    parent[rootB] = rootA;
                    size[rootA] = sizeTotal;
                } else {
                    parent[rootA] = rootB;
                    size[rootB] = sizeTotal;
                }
                return sizeTotal;
            }
            return size[rootA];
        }
    }

    public int longestConsecutive(int[] nums) {
        UnionFind unionFind = new UnionFind(nums.length);
        HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (int i : nums) {
            set.add(i);
            int size = 1;
            if (set.contains(i-1)) {
                size = unionFind.union(i,i-1);
            }
            if (set.contains(i+1)) {
                size = unionFind.union(i,i+1);
            }
            res = Math.max(res, size);
        }
        return res;
    }

    /// Try another sorting method;
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (int num : set) {

            if (!set.contains(num-1)) {
                int currLongest = 1;
                while(set.contains(num+1)) {
                    currLongest++;
                    num++;
                }
                res = Math.max(currLongest,res);
            }
        }
        return res;

    }
}
