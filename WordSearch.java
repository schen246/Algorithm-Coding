public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index + 1 == word.length()) {
            return true;
        }
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
                if (dfs(board, word, index + 1, x, y, visited)) {
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
    // time: O(k * 4^|word|) space: O(m * n)
}
