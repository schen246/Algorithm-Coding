public class LongestPalindromicSubstring {
    // M1: dp - time: O(n^2) space: O(n^2)
    // dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int len = 0, start = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j] && j - i + 1 > len) {
                    len = Math.max(len, j - i + 1);
                    start = i;
                }
            }
        }
        return s.substring(start, start + len);
    }

    // M2: expand around center - time: O(n^2) space: O(1)
    public String longestPalindrome2(String s) {
        int n = s.length();
        int res = 1, start = 0;
        for (int i = 1; i < n; i++) {
            int cur1 = expand(s, i, i);
            int cur2 = expand(s, i - 1, i);
            int len = Math.max(cur1, cur2);
            if (len > res) {
                res = len;
                start = i - len / 2;
            }
        }
        return s.substring(start, start + res);
    }
    
    private int expand(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}
