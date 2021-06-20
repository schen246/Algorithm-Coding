import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class NumberOfMatchingSubsequences {
    // M1: bf - time: O(s.length * sum(word[i].length)) space: O(1)
    public int numMatchingSubseq(String S, String[] words) {
        int res = 0;
        for (String word : words) {
            if (validSub(word, S)) {
                res++;
            }
        }
        return res;
    }
    
    private boolean validSub(String word, String s) {
        int i = 0, j = 0;
        while (i < word.length() && j < s.length()) {
            if (word.charAt(i) == s.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == word.length();
    }

    // M2: one pass - map + queue - time: O(s.length + sum(word[i].length!)) space: O(sum(word[i].length))
    public int numMatchingSubseq2(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.putIfAbsent((char)('a' + i), new ArrayDeque<>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).offer(word);
        }
        int res = 0;
        for (char c : S.toCharArray()) {
            Deque<String> q = map.get(c);
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.length() == 1) {
                    res++;
                } else {
                    map.get(cur.charAt(1)).offer(cur.substring(1));
                }
            }
        }
        return res;
    }
}
