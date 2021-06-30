public class PaintHouseII {
    // M1: dp - time: O(n * k * k) space: O(n * k)
    public int minCostII(int[][] costs) {
        int m = costs.length, n = costs[0].length;
        int[][] dp = new int[m + 1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k == j) {
                        continue;
                    }
                    cur = Math.min(cur, dp[i][k]);
                }
                if (cur == Integer.MAX_VALUE) {// there may be costs[0].length = 1
                    cur = 0;
                }
                dp[i + 1][j] = cur + costs[i][j];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[m][i]);
        }
        return res;
    }
}
