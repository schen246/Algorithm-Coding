public class MinimumDifficultyOfAJobSchedule {
    // dp - time: O(n * d) space: O(n * d)
    // dp[i][j]: min difficulty to finish i jobs in j days
    // dp[i][j] = min(dp[k][j - 1] + max(jobs[k, i - 1])) iff i >= j && dp[k][j - 1] != -1
    public int minDifficulty(int[] jobs, int d) {
        int n = jobs.length;
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= d; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = -1;
                } else if (i < j) {
                    dp[i][j] = -1;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    int cur = 0;
                    for (int k = i - 1; k >= j - 1; k--) {
                        cur = Math.max(cur, jobs[k]);
                        if (dp[k][j - 1] != -1) {
                            dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + cur);
                        }
                    }
                }
            }
        }
        return dp[n][d];
    }
}
