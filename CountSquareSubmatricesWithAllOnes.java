public class CountSquareSubmatricesWithAllOnes {
    // dp - time: O(m * n) space: O(m * n)
    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == 0) continue;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                res += dp[i][j];
            }
        }
        return res;
    }

    // dp - time: O(m * n) space: O(2 * n)
    public int countSquares2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[2][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == 0) {
                    dp[i % 2][j] = 0;
                } else {
                    dp[i % 2][j] = Math.min(Math.min(dp[(i - 1) % 2][j], dp[i % 2][j - 1]), dp[(i - 1) % 2][j - 1]) + 1;
                    res += dp[i % 2][j];
                }
            }
        }
        return res;
    }
}
