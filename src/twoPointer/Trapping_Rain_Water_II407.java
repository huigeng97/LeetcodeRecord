package twoPointer;


import java.util.PriorityQueue;

// Leave the next review to finish the two pointers (extension method);
public class Trapping_Rain_Water_II407 {

    // Trace all the boundary
    // if there is a neighbor is smaller than the boudary add water to make it the same level;
    public int trapRainWater(int[][] heightMap) {
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        if (m <= 2 || n <= 2) return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[2]-b[2]));
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i,0,heightMap[i][0]});
            pq.offer(new int[]{i,n-1,heightMap[i][n-1]});
            visit[i][0] = true;
            visit[i][n-1] = true;
        }
        for (int j = 1; j < n-1; j++) {
            pq.offer(new int[]{0,j,heightMap[0][j]});
            pq.offer(new int[]{m-1,j,heightMap[m-1][j]});
            visit[0][j] = true;
            visit[m-1][j] = true;
        }
        int water = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int height = curr[2];
            for (int[] dir : dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                // if this part could be separated by two parts; it would be very fast!;
                // one part is to visit the neighbors;
                // one part is to fill the water and offer into the queue;
                if (x >= 0 && x < m && y >= 0 && y < n && visit[x][y] == false) {
                    visit[x][y] = true;
                    int diff = height - heightMap[x][y];
                    if (diff > 0) {
                        water += diff;
                        pq.offer(new int[]{x, y, height});
                    } else {
                        pq.offer(new int[]{x, y, heightMap[x][y]});
                    }
                }
            }
        }
        return water;
    }

}
