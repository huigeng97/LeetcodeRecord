package DFS;

import java.util.*;

public class PacificAtlanticWaterFlow417 {

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    int m,n;
    int[][] heights;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        this.heights = heights;
        // very basic DFS we could use;
        // use two DFS and see which is in the intersection;
        m = heights.length;
        if (m == 0) {
            return null;
        }
        n = heights[0].length;

        boolean[][] alt = new boolean[m][n];
        boolean[][] pac = new boolean[m][n];

        for (int i = 0; i < heights.length; i++) {
            dfs(i, 0, pac);
        }

        for (int j = 0; j < heights[0].length; j++) {
            dfs(0, j, pac);
        }

        for (int i = m; i >= 0; i--) {
            dfs(i, n - 1, alt);
        }

        for (int j = heights[0].length - 1; j >= 0; j--) {
            dfs(m - 1, j, alt);
        }




        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (alt[i][j] && pac[i][j]) {
                    res.add(Arrays.asList(i,j));
                }
            }
        }
        return res;
    }

    private void dfs(int i, int j, boolean[][] pac) {

        pac[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || x >= m || y < 0 || y >= n) { continue;}
            if (pac[x][y]) { continue; }
            if (heights[x][y] >= heights[i][j]) { dfs(x, y, pac); }


        }
    }
}
