package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Geng Hui
 */
public class SpiralMatrixII59 {
  public int[][] generateMatrix(int n) {

      int[][] res = new int[n][n];
      generateMatrix(res,0,0,n-1,n-1,1);
      return res;
  }

    private void generateMatrix(int[][] matrix, int startX, int startY, int endX, int endY, int num) {

        if (startX > endX || startY > endY) {
            return;
        }
        for (int i = startY; i <= endY; i++) {
            matrix[startX][i] = num++;
        }
        if (startX != endX) {
            for (int j = startX + 1; j <= endX - 1; j++) {
                (matrix[j][endY]) = num++;
            }
            for (int i = endY; i >= startY; i--) {
                (matrix[endX][i]) = num++;
            }
            if (startY != endY) {
                for (int j = endX - 1; j >= startX + 1; j--) {
                    (matrix[j][startY]) = num++;
                }
            }
        }
        generateMatrix(matrix,startX+1,startY+1,endX-1,endY-1,num);
    }


    // This is an alternative method;
    // It is iterative method we could use the layer to iteration;
    // The we could always get the layer number by (num + 1 ) / 2 and start with 0, end with i < layer;

    public int[][] generateMatrix2(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        for (int layer = 0; layer < (n + 1) / 2; layer++) {
            // direction 1 - traverse from left to right
            for (int ptr = layer; ptr < n - layer; ptr++) {
                result[layer][ptr] = cnt++;
            }
            // direction 2 - traverse from top to bottom
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[ptr][n - layer - 1] = cnt++;
            }
            // direction 3 - traverse from right to left
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[n - layer - 1][n - ptr - 1] = cnt++;
            }
            // direction 4 - traverse from bottom to top
            for (int ptr = layer + 1; ptr < n - layer - 1; ptr++) {
                result[n - ptr - 1][layer] = cnt++;
            }
        }
        return result;
    }
}
