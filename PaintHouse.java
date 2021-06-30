public class PaintHouse {
    // M1: dp - time: O(n * 3) space: O(n * 3)
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = Math.min(dp[i][1], dp[i][2]) + costs[i][0];
            dp[i + 1][1] = Math.min(dp[i][0], dp[i][2]) + costs[i][1];
            dp[i + 1][2] = Math.min(dp[i][0], dp[i][1]) + costs[i][2];
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }
    // M2: recursion + memo - time: O(n * 3) = O(n) space: O(n * 3) = O(n)
    public int minCost2(int[][] costs) {
        int[][] memo = new int[costs.length + 1][3];
        int[] res = dfs(costs.length, costs, memo);
        return Math.min(res[0], Math.min(res[1], res[2]));
    }
    
    private int[] dfs(int index, int[][] costs, int[][] memo) {
        if (index == 0) {
            return new int[]{0, 0, 0};
        }
        if (!(memo[index][0] == 0 &&  memo[index][1] == 0 && memo[index][2] == 0)) {
            return memo[index];
        }
        int[] cur = dfs(index - 1, costs, memo);
        int[] res = new int[3];
        res[0] = Math.min(cur[1], cur[2]) + costs[index - 1][0];
        res[1] = Math.min(cur[0], cur[2]) + costs[index - 1][1];
        res[2] = Math.min(cur[0], cur[1]) + costs[index - 1][2];
        memo[index] = res;
        return res;
    }
}
