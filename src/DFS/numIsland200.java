package DFS;

public class numIsland200 {
    private boolean[][] seen;
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        this.seen = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (seen[i][j] == false && grid[i][j] == '1') {
                    DFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void DFS(char[][] grid, int a, int b) {
        if (seen[a][b] = true) return;
        seen[a][b] = true;
        if (grid[a][b] == '1') {
            if (a >= 1) DFS(grid, a - 1, b);
            if (a <= grid.length - 2) DFS(grid, a + 1, b);
            if (b >= 1) DFS(grid, a, b - 1);
            if (b <= grid[0].length - 2) DFS(grid, a, b + 1);
        }
    }
}
