package DP2;

public class PaintHouse256 {

    public int minCost(int[][] costs) {

        int m = costs.length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                costs[i][j] += Math.min(costs[i - 1][(1 + j) % 3], costs[i - 1][(2 + j) % 3]);
            }
        }
        return Math.min(costs[m - 1][0], Math.min(costs[m - 1][1],costs[m - 1][2]));
    }

}
