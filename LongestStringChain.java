import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestStringChain {
    // M1: dp - time: O(n^2 * L) space: O(n)
    public int longestStringChain(String[] words) {
        Map<String, Integer> memo = new HashMap<>();
        Map<Integer, List<String>> map = new HashMap<>();
        int maxLen = 0;
        for (String word : words) {
            int len = word.length();
            maxLen = Math.max(maxLen, len);
            map.putIfAbsent(len, new ArrayList<>());
            map.get(len).add(word);
            memo.put(word, 1);
        }
        int res = 1;
        for (int i = 0; i <= maxLen; i++) {
            if (map.get(i) == null) continue;
            for (String word : map.get(i)) {
                int preLen = i - 1;
                if (map.get(preLen) == null) break;
                for (String preWord : map.get(preLen)) {
                    if (valid(preWord, word)) {
                        memo.put(word, Math.max(memo.get(word), memo.get(preWord) + 1));
                        res = Math.max(res, memo.get(word));
                    }
                }
            }
        }
        return res;
    }

    private boolean valid(String s1, String s2) {
        int i = 0, j = 0;
        boolean found = false;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else if (found) {
                return false;
            } else {
                found = true;
                j++;
            }
        }
        return true;
    }

    // M2: sort + dp - time: O(nlogn + n * L^2) space: O(n)
    public int longestStringChain2(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {// O(n)
            int max = 1;
            for (int i = 0; i < word.length(); i++) {// O(L)
                String str = word.substring(0, i) + word.substring(i + 1);// O(L)
                max = Math.max(max, map.getOrDefault(str, 0) + 1);
            }
            map.put(word, max);
            res = Math.max(res, max);
        }
        return res;
    }
}
