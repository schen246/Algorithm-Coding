public class GameOfLife {
    // 2d in place - time: O(m * n) space: O(1)
    public void gameOfLife(int[][] board) {
        // -1: 0 -> 1
        // -2: 1 -> 0
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nextState(board, i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                } else if (board[i][j] == -2) {
                    board[i][j] = 0;
                }
            }
        }
    }
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
    private void nextState(int[][] board, int i, int j) {
        int cnt = 0;
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if (board[x][y] == 1 || board[x][y] == -2) {
                    cnt++;
                }
            }
        }
        if (board[i][j] == 0 && cnt == 3) {
                board[i][j] = -1;   
        } else if (board[i][j] == 1 && (cnt < 2 || cnt > 3)) {
                board[i][j] = -2;
        }
    }

    // Follow Up: if board going to be infinite
    // 1. computationally impossible to iterate a matrix that large -> map reduce
    // 2. impossible to store big matrix in memory -> distributed server or three rows each time
    // 3. waste space if only few live cells within extremely sparse matrix -> save location of live cells and apply 4 rules
}
