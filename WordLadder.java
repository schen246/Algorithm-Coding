import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord) || beginWord == null) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        Queue<String> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        q.offer(beginWord);
        set.add(beginWord);
        int depth = 1;
        while (q.size() > 0) {
            int n = q.size();
            depth++;
            for (int i = 0; i < n; i++) {
                String cur = q.poll();
                for (String s : getNeighbors(words, cur)) {
                    if (s.equals(endWord)) {
                        return depth;
                    }
                    if (set.add(s)) {
                        q.offer(s);
                    }
                }
            }
        }
        return 0;
    }

    private List<String> getNeighbors(Set<String> words, String cur) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < cur.length(); i++) {//O(L)
            char[] arr = cur.toCharArray();
            for (int j = 0; j < 26; j++) {//O(26)
                arr[i] = (char)('a' + j);
                String word = new String(arr);//O(L)
                if (words.contains(word)) {
                    res.add(word);
                }
            }
        }
        return res;
    }

    // time: O(n * 26 * L^2)
    // space: O(n)
}
