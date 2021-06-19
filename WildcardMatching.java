public class WildcardMatching {
    // M1: recursion + memo - time: O(s * p) space: O(s * p)
    public boolean isMatch(String s, String p) {
        int[][] memo = new int[s.length() + 1][p.length() + 1];// -1: false 0: not visited 1: true
        return dfs(s, s.length(), p, p.length(), memo);
    }

    private boolean dfs(String s, int index1, String p, int index2, int[][] memo) {
        // case 0
        if (memo[index1][index2] != 0) {
            return memo[index1][index2] == 1;
        }
        // case 1
        if (index1 == 0 && index2 == 0) {
            memo[0][0] = 1;
            return true;
        }
        // case 2
        if (index2 == 0) {
            memo[index1][0] = -1;
            return false;
        }
        // case 3
        if (index1 == 0) {
            if (p.charAt(index2 - 1) != '*') {
                memo[0][index2] = -1;
                return false;
            }
            if (dfs(s, index1, p, index2 - 1, memo)) {
                memo[0][index2] = 1;
                return true;
            }
            memo[0][index2] = -1;
            return false;
        }
        char ch = p.charAt(index2 - 1);
        if (ch == '?') {
            if (dfs(s, index1 - 1, p, index2 - 1, memo)) {
                memo[index1][index2] = 1;
                return true;
            }
            memo[index1][index2] = -1;
            return false;
        } else if (ch == '*') {
            for (int i = index1; i >= 0; i--) {
                if (dfs(s, i, p, index2 - 1, memo)) {
                    memo[index1][index2] = 1;
                    return true;
                }
            }
            memo[index1][index2] = -1;
            return false;
        } else {
            if (s.charAt(index1 - 1) == p.charAt(index2 - 1)) {
                if (dfs(s, index1 - 1, p, index2 - 1, memo)) {
                    memo[index1][index2] = 1;
                    return true;
                }
            }
            memo[index1][index2] = -1;
            return false;
        }
    }

    // M2: dp - time: O(s * p) space: O(s * p)
    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        for (int i = 0; i <= p.length(); i++) {
            for (int j = 0; j <= s.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    if (p.charAt(i - 1) == '*') {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    char ch = p.charAt(i - 1);
                    if (ch == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (ch == '*') {
                        for (int k = j; k >= 0; k--) {
                            if (dp[i - 1][k]) {
                                dp[i][j] = true;
                                break;
                            }
                        }
                    } else {
                        if (p.charAt(i - 1) == s.charAt(j - 1)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    // M3: backtracking + early return - time: O(s.length() ^ p.length()) space: O(p.length())
    public boolean isMatch3(String s, String p) {
        return dfs(s, 0, p, 0);
    }
    
    private boolean dfs(String s, int index1, String p, int index2) {
        if (index1 == s.length() && index2 == p.length()) {
            return true;
        }
        if (index2 == p.length()) {
            return false;
        }
        if (index1 == s.length()) {
            if (p.charAt(index2) != '*') {
                return false;
            }
            return dfs(s, index1, p, index2 + 1);
        }
        char ch = p.charAt(index2);
        if (ch == '?') {
            return dfs(s, index1 + 1, p, index2 + 1);
        } else if (ch == '*') {
            for (int i = index1; i <= s.length(); i++) {
                if (dfs(s, i, p, index2 + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (s.charAt(index1) == p.charAt(index2)) {
                return dfs(s, index1 + 1, p, index2 + 1);
            }
            return false;
        }
    }
}
