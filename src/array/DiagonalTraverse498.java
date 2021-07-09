package array;

/**
 * @author Geng Hui
 *
 */
public class DiagonalTraverse498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int layer = m + n - 1;
        int order = 1;
        int[] res = new int[m*n];
        int curr = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < layer; i++) {
            if (order == 1) {
                // read the matrix from the left-down to right-up;
                while (x >= 0 && y < n) {
                    res[curr++] = mat[x--][y++];
                }
                // initialize for the next part;
                if ( y == n) {
                    y--;
                    x = x + 2;
                }
                else if (x < 0 ) {
                    x++;
                }

                order = 0;
            } else {
                // read the matrix from the right-up to left-down;
                while (x < m && y >= 0) {
                    res[curr++] = mat[x++][y--];
                }
                // initialize for the next part;
                if ( x == m) {
                    x--;
                    y = y + 2;
                } else if (y < 0 ) {
                    y++;
                }

                order = 1;
            }
        }
        return res;
    }
}
