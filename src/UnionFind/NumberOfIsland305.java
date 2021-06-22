package UnionFind;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIsland305 {
    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        if (positions == null ||positions.length == 0 || positions[0].length == 0) return null;
        UnionFind unionFind = new UnionFind(m*n);
        List<Integer> res = new ArrayList<>();
        int[] map = new int[m*n];
        int count = 0;
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            int curr = i*n+j;
            if (map[curr] == 1) {
                res.add(count);
                continue;
            }
            count++;
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                int nb = x * n + y;
                if(0 <= x && x < m && 0<= y && y < n && map[nb] == 1) {
                    if (unionFind.union(curr, nb)) {
                        count--;
                    }
                }

            }
            // union find with all the neighbors left and up;
            // if they are the different root; res--;
            res.add(count);
            map[curr] = 1;
        }
        return res;
    }

    private int getID(int x, int y, int m, int n, int[] map) {
        if (0 <= x && x < m && 0<= y && y < n && map[x*n+y] == 1) {
            return x*n+y;
        }
        return -1;
    }


}
