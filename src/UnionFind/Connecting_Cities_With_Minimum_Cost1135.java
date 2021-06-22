package UnionFind;

import java.util.Arrays;

public class Connecting_Cities_With_Minimum_Cost1135 {
    public int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, (a, b) -> a[2]-b[2]);
        int numedge = 0, cost = 0;

        UnionFind unionFind = new UnionFind(n);

        for (int[] edge : connections) {

            if (unionFind.union(edge[0],edge[1])) {
                cost += edge[2];
                numedge ++;
                if (numedge == n - 1) return cost;
            }
        }

        return -1;
    }
}
