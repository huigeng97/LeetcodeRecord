package binary;


// This is another very good question;
// Think about the binary search
// Think about the time complexity;
// it is easier to finish within (m + n);
// but think if we could do it better like log(m) + log(n);
// so there is the binary search: once we have an input with a sorted pattern. Then we could always use the binary search
public class Search_a_2D_Matrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int lo = 0;
        int hi = matrix.length*matrix[0].length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int col = mid / matrix[0].length;
            int row = mid % matrix[0].length;
            if (matrix[col][row] == target) {
                return  true;
            }
            if (matrix[col][row] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }
}
