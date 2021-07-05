public class BurstBalloons {
    // M1: dp - time: O(n^2) space: O(n^2)
    // dp[i][j]: max coins can collect by bursting balloons of nums(i, j) NOT including i, j
    // return dp[0][end]
    // dp[i][j] = max(dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j])
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n + 2; j++) {
                int cur = 0;
                for (int k = i + 1; k <= j - 1; k++) {
                    cur = Math.max(cur, dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j]);
                }
                dp[i][j] = cur;
            }
        }
        return dp[0][n + 1];
    }
}
