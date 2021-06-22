package UnionFind;

public class NumberOfIsland200 {
    public int numIslands(char[][] grid) {
        if (grid == null ||grid.length == 0 || grid[0].length == 0) return 0;
        UnionFind unionFind = new UnionFind(grid.length*grid[0].length);
        int res = 0;
        int n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res++;
                    if (i != 0 && grid[i-1][j] == 1) {
                        if (unionFind.union(i*n+j, (i-1)*n+j)) res--;
                    }
                    if (j != 0 && grid[i][j-1] == 1) {
                        if (unionFind.union(i*n+j, i*n+(j-1))) res--;
                    }
                    // union find with all the neighbors left and up;
                    // if they are the different root; res--;
                }
            }
        }
        return res;
    }
}
