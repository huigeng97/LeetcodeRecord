package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeros73 {

    public void setZeroes(int[][] matrix) {

        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; i < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    xSet.add(i);
                    ySet.add(j);
                }
            }
        }
        
        for (Integer x : xSet) {
            Arrays.fill(matrix[x] , 0);
        }
        for (Integer y : ySet) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][y] = 0;
            }
        }



    }

    // Follow up : Could you devise a constant space solution?
    // The actual solution is much easier than I thought;
    // same idea like the previous one, but use the first element as a flag to replace the set;

}
