public class UniquePaths {
    // M1: dfs + memo - time: O(m * n) space: O(m + 1) + O(m * n) memo
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        return dfs(0, 0, memo);
    }

    private int dfs(int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (i == memo.length - 1 && j == memo[0].length - 1) {
            return 1;
        }
        int res = 0;
        if (i + 1 < memo.length) {
            res += dfs(i + 1, j, memo);
        }
        if (j + 1 < memo[0].length) {
            res += dfs(i, j + 1, memo);
        }
        memo[i][j] = res;
        return res;
    }

    // M2: dp - time: O(m * n) space: O(m * n)
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // M3: dp - time: O(m * n) space: O(n)
    public int uniquePaths3(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
