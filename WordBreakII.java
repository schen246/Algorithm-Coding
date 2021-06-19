import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
    // M1: recursion + memo
    // time: O(words.length) + O(2^n * n) + O(n^2) space: O(2^n * n) + O(n^2) + O(words.length)
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();// index, list of string of word breaks
        List<String> init = new ArrayList<>();
        init.add("");
        memo.put(0, init);
        return dfs(s, s.length(), words, memo);
    }

    private List<String> dfs(String s, int index, Set<String> words, Map<Integer, List<String>> memo) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        List<String> res = new ArrayList<>();
        for (int i = index - 1; i >= 0; i--) {
            String sub = s.substring(i, index);
            if (words.contains(sub)) {
                for (String str : dfs(s, i, words, memo)) {
                    if (i == 0) {
                        res.add(str + sub);
                    } else {
                        res.add(str + " " + sub);
                    }
                }
            }
        }
        memo.put(index, res);
        return res;
    }

    // M2: dp - time: O(2^n * n) space: O(2^n * n)
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        DP[] dp = new DP[s.length() + 1];
        dp[0] = new DP("");
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = new DP();
            for (int j = i - 1; j >= 0; j--) {
                String cur = s.substring(j, i);
                if (words.contains(cur)) {
                    for (String sub : dp[j].path) {
                        if (j == 0) {
                            dp[i].path.add(sub + cur);
                        } else {
                            dp[i].path.add(sub + " " + cur);
                        }
                    }
                }
            }
        }
        return dp[s.length()].path;
    }
}

class DP {
    List<String> path;
    public DP() {
        path = new ArrayList<>();
    }
    
    public DP(String s) {
        path = new ArrayList<>();
        path.add(s);
    }
}