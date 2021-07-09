package array;

import java.util.ArrayList;
import java.util.List;

/** @author Geng Hui */

/** @author Geng Hui This fuction is to read a matrix spirally, */
public class SpiralMatrix54 {

  public static void main(String[] args) {
    //
    SpiralMatrix54 test = new SpiralMatrix54();
    test.spiralOrder(new int[][] {{0}, {1}, {2}});
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    return spiralOrder(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, new ArrayList<>());
  }

  private List<Integer> spiralOrder(
      int[][] matrix, int startX, int startY, int endX, int endY, List<Integer> temp) {
    if (startX > endX || startY > endY) {
      return temp;
    }
    for (int i = startY; i <= endY; i++) {
      temp.add(matrix[startX][i]);
    }
    if (startX != endX) {
      for (int j = startX + 1; j <= endX - 1; j++) {
        temp.add(matrix[j][endY]);
      }
      for (int i = endY; i >= startY; i--) {
        temp.add(matrix[endX][i]);
      }
      if (startY != endY) {
        for (int j = endX - 1; j >= startX + 1; j--) {
          temp.add(matrix[j][startY]);
        }
      }
    }
    return spiralOrder(matrix, startX + 1, startY + 1, endX - 1, endY - 1, temp);
  }
}
