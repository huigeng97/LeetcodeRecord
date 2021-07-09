package array;

/**
 * @author Geng Hui
 */
public class RotateImage48 {

    // Because we are tracing the element anticlockwise, we must iterate item from the end of each line;

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int layer = (n+1) / 2;
        for (int i = 0; i < layer; i++) {
            int[] temp = new int[n - 2 * i];
            for (int j = n - i - 1; j >= i; j--) {
                temp[j-i] = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
            }
            for (int j = n - i - 1; j >= i; j--) {
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
            }
            for (int j = n - i - 1; j >= i; j--) {
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
            }
            for (int j = n - i - 1; j >= i; j--) {
                matrix[j][n - 1 - i] = temp[j-i];
            }
        }
    }

  public static void main(String[] args) {
    //
      RotateImage48 test = new RotateImage48();
      test.rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
  }
}
