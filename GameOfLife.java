public class GameOfLife {
    // 2d in place - time: O(m * n) space: O(1)
    public void gameOfLife(int[][] board) {
        // 0 cur dead future dead
        // 1 cur live future dead
        // 2 cur dead future live
        // 3 cur live future live
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nextState(board, i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    
    private void nextState(int[][] board, int i, int j) {
        int cnt = 0;
        if (board[i][j] == 0) {
            for (int[] dir : DIRS) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                    if (board[x][y] == 1 || board[x][y] == 3) {
                        cnt++;
                    }
                }
            }
            board[i][j] = cnt == 3 ? 2 : 0;
        } else {
            for (int[] dir : DIRS) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                    if (board[x][y] == 1 || board[x][y] == 3) {
                        cnt++;
                    }
                }
            }
            if (cnt == 2 || cnt == 3) {
                board[i][j] = 3;
            }
        }
    }
}
