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
}
