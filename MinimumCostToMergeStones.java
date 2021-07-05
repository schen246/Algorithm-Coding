public class MinimumCostToMergeStones {
    // dp - time: O(n^3 * k) space: O(n^2 * k)
    // dp[i][j][k]: min cost to merge stones[i~j] into k piles
    // dp[i][j][k] = min(dp[i][m][1] + dp[m + 1][j][k - 1]) if 2 <= k <= size
    // dp[i][j][1] = dp[i][j][size] + sum(stones[i~j])
    public int mergeStones(int[] stones, int size) {
        int n = stones.length;
        if (n <= 1) return 0;
        if ((n - 1) % (size - 1) != 0) return -1;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }
        int[][][] dp = new int[n][n][size + 1];
        for (int len = 2; len <= n; len++) {// len of range
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // [i~j]
                for (int k = 2; k <= size; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                    for (int m = i; m < j; m += size - 1) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][size] + sum[j + 1] - sum[i];
            }
        }
        return dp[0][n - 1][1];
    }
}
