package backtracking;

/**
 * @author Geng Hui
 */
public class SudokuSolver44 {
    // don't be afraid, do it what you think;
    // one trick : char c = '1'; c <= '9'; c++; char could be also iterated!
    // separate the

    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }
        backtracking(board);
    }

    private boolean backtracking(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for  (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board,i,j,c)) {
                            board[i][j] = c;
                            if (backtracking(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char k) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == k) {return false;}
            if (board[i][col] == k) {return false;}
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == k) {return false;}
        }
        return true;
    }
}
