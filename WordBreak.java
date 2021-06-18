import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    // M1: recursion + memo - time: O(n^2 * n) space: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int[] memo = new int[s.length() + 1];// -1: false 0: not visited 1: true
        memo[0] = 1;
        return dfs(s, s.length(), words, memo);
    }

    private boolean dfs(String s, int index, Set<String> words, int[] memo) {
        if (memo[index] != 0) {
            return memo[index] == 1;
        }
        for (int i = index - 1; i >= 0; i--) {
            String str = s.substring(i, index);
            if (words.contains(str)) {
                if (dfs(s, i, words, memo)) {
                    memo[index] = 1;
                    return true;
                }
            }
        }
        memo[index] = -1;
        return false;
    }

    // M2: dp - time: O(n * n* n) space: O(n)
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
