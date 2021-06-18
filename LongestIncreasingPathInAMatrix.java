public class LongestIncreasingPathInAMatrix {
    // dfs + memo - time: O(m * n) space: O(m * n)
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int res = 0;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, i, j, memo));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int res = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[i][j] < matrix[x][y]) {
                res = Math.max(res, dfs(matrix, x, y, memo) + 1);
            }
        }
        memo[i][j] = res;
        return res;
    }
}
