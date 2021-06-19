public class EditDistance {
    // M1: dp - time: O(m * n) space: O(m * n)
    // dp[i][j]: min operations to convert word1[0, i - 1] to word2[0, j - 1]
    // dp[0][0] = 0, dp[0][j] = dp[0][j - 1] + 1, dp[i][0] = dp[i - 1][0] + 1
    // if word1[i - 1] == word2[j - 1]: dp[i][j] = dp[i - 1][j - 1]  
    // otherwise: dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    }
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    // M2: recursion + memo - time: O(m * n) space: O(m * n)
    public int minDistance2(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        return dfs(word1, word1.length(), word2, word2.length(), memo);
    }
    
    private int dfs(String word1, int index1, String word2, int index2, int[][] memo) {
        if (memo[index1][index2] != 0) {
            return memo[index1][index2];
        }
        if (index1 == 0) {
            memo[0][index2] = index2;
            return index2;
        }
        if (index2 == 0) {
            memo[index1][0] = index1;
            return index1;
        }
        int res = Integer.MAX_VALUE;
        if (word1.charAt(index1 - 1) == word2.charAt(index2 - 1)) {
            res = dfs(word1, index1 - 1, word2, index2 - 1, memo);
        } else {
            res = Math.min(res, dfs(word1, index1 - 1, word2, index2, memo) + 1);// delete
            res = Math.min(res, dfs(word1, index1, word2, index2 - 1, memo) + 1);// add
            res = Math.min(res, dfs(word1, index1 - 1, word2, index2 - 1, memo) + 1);// replace
        }
        memo[index1][index2] = res;
        return res;
    }
}
