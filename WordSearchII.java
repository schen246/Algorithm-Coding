public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (wordSearch(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean wordSearch(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, 0, word, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean dfs(char[][] board, int i, int j, int index, String word, boolean[][] visited) {
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
                if (dfs(board, x, y, index + 1, word, visited)) {
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
    // time: O(words.length * k * 4^word.length) space: O(m * n)
}
