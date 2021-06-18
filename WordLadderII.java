import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return res;
        }
        // bfs - build map with shortest path to each word
        Map<String, List<String>> map = new HashMap<>();
        buildGraph(beginWord, endWord, words, map);// O(words.length * 26 * L * L) space: O(words.length)
        if (!map.containsKey(endWord)) {
            return res;
        }
        // dfs find all shortest paths
        List<String> cur = new ArrayList<>();
        dfs(endWord, beginWord, cur, res, map);// O(V + E) space: O(words.length) in worst case
        return res;
    }

    private void dfs(String word, String beginWord, List<String> cur, List<List<String>> res, Map<String, List<String>> map) {
        if (word.equals(beginWord)) {
            cur.add(word);
            List<String> temp = new ArrayList<>(cur);
            Collections.reverse(temp);
            res.add(temp);
            cur.remove(cur.size() - 1);
            return;
        }
        cur.add(word);
        for (String nei : map.get(word)) {
            dfs(nei, beginWord, cur, res, map);
        }
        cur.remove(cur.size() - 1);
    }

    private void buildGraph(String beginWord, String endWord, Set<String> words, Map<String, List<String>> map) {
        // bfs - shortest path map
        Queue<String> q = new ArrayDeque<>();
        Map<String, Integer> dist = new HashMap<>();
        q.offer(beginWord);
        dist.put(beginWord, 0);
        map.put(beginWord, new ArrayList<>());
        int steps = 0;
        while (q.size() > 0) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(endWord)) {
                    return;
                }
                for (String nei : getNei(cur, words)) {
                    if (!dist.containsKey(nei)) {
                        q.offer(nei);
                        dist.put(nei, steps);
                    }
                    map.putIfAbsent(nei, new ArrayList<>());
                    if (dist.get(cur) + 1 == dist.get(nei)) {
                        map.get(nei).add(cur);
                    }
                }
            }
        }
    }

    private List<String> getNei(String cur, Set<String> words) {// O(26 * L * L)
        List<String> res = new ArrayList<>();
        for (int i = 0; i < cur.length(); i++) {// O(word.length)
            char[] arr = cur.toCharArray();
            for (int j = 0; j < 26; j++) {// O(26)
                char ch = (char)('a' + j);
                if (arr[i] == ch) {
                    continue;
                }
                arr[i] = ch;
                String str = new String(arr);// O(word.length)
                if (words.contains(str)) {
                    res.add(str);
                }
            }
        }
        return res;
    }
}
