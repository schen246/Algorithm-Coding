package Amazon.Coding;

public class DesignTicTacToe {
    int[][] board;// space: O(n^2)
    int n;
    public DesignTicTacToe(int n) {
        board = new int[n][n];
        this.n = n;
    }
    
    public int move(int row, int col, int player) {// time: O(n)
        board[row][col] = player;
        if (checkRow(row, player) || checkCol(col, player) || checkDiag(player) || checkAntiDiag(player)) {
            return player;
        }
        return 0;
    }
    
    private boolean checkRow(int row, int player) {
        for (int i = 0; i < n; i++) {
            if (board[row][i] != player) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkCol(int col, int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][col] != player) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkDiag(int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][i] != player) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkAntiDiag(int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][n - 1 - i] != player) {
                return false;
            }
        }
        return true;
    }
}

class TicTacToe2 {

    int[] rows, cols;// space: O(n)
    int diag, antiDiag;
    public TicTacToe2(int n) {
        rows = new int[n];
        cols = new int[n];
        diag = 0;
        antiDiag = 0;
    }
    
    public int move(int row, int col, int player) {// time: O(1)
        int cur = player == 1 ? 1 : -1;
        rows[row] += cur;
        cols[col] += cur;
        if (row == col) {
            diag += cur;
        }
        int n = rows.length;
        if (row + col == n - 1) {
            antiDiag += cur;
        }
        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diag) == n || Math.abs(antiDiag) == n) {
            return player;
        }
        return 0;
    }
}
