public class RegularExpressionMatching {
    // dfs + memo - time: O(s * p) space: O(s * p)
    public boolean isMatch(String s, String p) {
        int[][] memo = new int[s.length() + 1][p.length() + 1];
        return dfs(s, 0, p, 0, memo);
    }
    
    private boolean dfs(String s, int index1, String p, int index2, int[][] memo) {
        if (memo[index1][index2] != 0) {
            return memo[index1][index2] == 1;
        }
        if (index1 == s.length() && index2 == p.length()) {
            memo[index1][index2] = 1;
            return true;
        }
        if (index2 == p.length()) {
            memo[index1][index2] = -1;
            return false;
        }
        boolean match = index1 < s.length() && (s.charAt(index1) == p.charAt(index2) || p.charAt(index2) == '.');
        if (index2 + 1 < p.length() && p.charAt(index2 + 1) == '*') {
            if (dfs(s, index1, p, index2 + 2, memo) || (match && dfs(s, index1 + 1, p, index2, memo))) {
                memo[index1][index2] = 1;
                return true;
            }
            memo[index1][index2] = -1;
            return false;
        }
        if (match && dfs(s, index1 + 1, p, index2 + 1, memo)) {
            memo[index1][index2] = 1;
            return true;
        }
        memo[index1][index2] = -1;
        return false;
    }

    // dp - time: O(m * n) space: O(m * n)
    // dp[i][j]: whether s[i, end] matches p[j, end]
    // j + 1 == *: dp[i][j] = dp[i][j + 2] || (match && dp[i + 1][j])
    // match: dp[i][j] = dp[i + 1][j + 1]
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m && j == n) {
                    dp[i][j] = true;
                } else if (j == n) {
                    dp[i][j] = false;
                } else {
                    boolean match = i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                    if (j + 1 < n && p.charAt(j + 1) == '*') {
                        dp[i][j] = dp[i][j + 2] || (match && dp[i + 1][j]);
                    } else if (match) {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                }
            }
        }
        return dp[0][0];
    }
}
