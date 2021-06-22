package BackTracking;

public class WordSearch79 {

    private char[][] board;
    private String word;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        for (int row = 0; row < board.length; ++row)
            for (int col = 0; col < board[0].length; ++col)
                if (backtrack(0, row, col)) return true;
        return false;
    }

    private boolean backtrack(int index, int row, int col) {
        if (index == word.length()) {
            return true;
        }

        if (row < 0 || row == board.length || col < 0 || col == board[0].length
                || this.board[row][col] != word.charAt(index))
            return false;

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        this.board[row][col] = '#';
        boolean exist = false;
        for (int[] dir : dirs) {
            exist = exist || (backtrack(index+1, row+dir[0], col+dir[1]));
        }
        this.board[row][col] = word.charAt(index);
        return exist;
    }


    // This method is pretty faster (beat 90%)
    public boolean exist2(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] = '#';
        boolean exist = exist(board, y, x+1, word, i+1)
                || exist(board, y, x-1, word, i+1)
                || exist(board, y+1, x, word, i+1)
                || exist(board, y-1, x, word, i+1);
        board[y][x] = word[i];
        return exist;
    }
}
