package DFS;

public class maxAreaOfIsland695 {
    private int[][] grid;
    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        if (grid.length == 0 || grid[0].length == 0 ) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    int area = getArea(i, j);
                    System.out.println(area);
                    max = Math.max(area, max);
                }
            }
        }
        return max;
    }

    public int getArea(int i, int j) {
        int area = 0;
        if (grid[i][j] == '0') return 0;
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            area += 1;
            if (i >= 1) area += getArea(i - 1, j);
            if (i <= grid.length - 2) area += getArea(i + 1, j);
            if (j >= 1) area += getArea(i, j - 1);
            if (j <= grid[0].length - 2) area += getArea(i, j + 1);
        }
        return area;
    }
}
