package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // M1: sort + dp - time: O(nlogn) + O(n * L^3) space: O(n) + O(L)
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (canBreak(word, set)) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    
    private boolean canBreak(String word, Set<String> set) {
        if (set.isEmpty() || set.contains(word)) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
}
