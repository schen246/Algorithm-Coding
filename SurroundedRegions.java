private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

// bfs - time: O(m * n) space: O(m * n)
public void solve(char[][] board) {
    if(board.length == 0 || board[0].length == 0) {
        return;
    }
    int m = board.length, n = board[0].length;
    Queue<int[]> q = new ArrayDeque<>();
    // initial
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] == 'O') {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    board[i][j] = '#';
                    q.offer(new int[]{i, j});
                }
            }
        }
    }
    // search
    while (q.size() > 0) {
        int[] cur = q.poll();
        for (int[] dir : DIRS) {
            int x = cur[0] + dir[0], y = cur[1] + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                board[x][y] = '#';
                q.offer(new int[]{x, y});
            } 
        }
    }
    // post process
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] == 'O') {
                board[i][j] = 'X';
            } else if (board[i][j] == '#') {
                board[i][j] = 'O';
            }
        }
    }
}