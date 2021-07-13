package UnionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Leetcode Crashed.... Wait for tmr;

//This problem is not a normal Medium problem,
//Not easy to think a solution,
//But still doable;
public class PathWithMinimumEffort1631 {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] edges = new int[(n-1)*m+(m-1)*n][3];

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i+1 < m) {
                    edges[count][0] = i*n+j;
                    edges[count][1] = i*n+j+n;
                    edges[count][2] = Math.abs(heights[i][j]-heights[i+1][j]);
                    count++;
                }
                if (j+1 < n) {
                    edges[count][0] = i*n+j;
                    edges[count][1] = i*n+j+1;
                    edges[count][2] = Math.abs(heights[i][j]-heights[i][j+1]);
                    count++;
                }
            }
        }

        Arrays.sort(edges,(a,b) -> a[2]-b[2]);

        UnionFind unionFind = new UnionFind(m*n);
        for (int i = 0; i < edges.length; i++) {
            unionFind.union(edges[i][0],edges[i][1]);
            if (unionFind.connected(0,n*m-1)) return edges[i][2];
        }
        return 0;
    }
}


