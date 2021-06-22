package UnionFind;

public class Surrounded_Regions130 {
    // pretty interesting solution with the unionFind;
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        UnionFind unionFind = new UnionFind(n * m + 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                    unionFind.union(i * n + j, n * m);
                } else if (board[i][j] == 'O') {
                    if (board[i - 1][j] == 'O')
                        unionFind.union(i * n + j, (i - 1) * n + j);
                    if (board[i + 1][j] == 'O')
                        unionFind.union(i * n + j, (i + 1) * n + j);
                    if (board[i][j - 1] == 'O')
                        unionFind.union(i * n + j, i * n + j - 1);
                    if (board[i][j + 1] == 'O')
                        unionFind.union(i * n + j, i * n + j + 1);
                }

            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    if (!unionFind.connected(i * n + j, n * m)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
}

