import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        // assume: each word in words not empty, "ab" should be smaller than "abc"
        // build graph
        Map<Character, Set<Character>> map = new HashMap<>();
        int[] d = new int[26];// assume only lower case english letters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (map.get(c) == null) {
                    map.put(c, new HashSet<>());
                }
            }
        }
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());
            for (int j = 0; j < len; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    char out = first.charAt(j);
                    char in = second.charAt(j);
                    // out -> in
                    if (!map.get(out).contains(in)) {
                        map.get(out).add(in);
                        d[in - 'a']++;
                    }
                    break;
                }
                if (j == len - 1 && first.length() > second.length()) {
                    return "";
                }
            }
        }
        // topo sort
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new ArrayDeque<>();// if normal order for un-relational char, use PriorityQueue
        for (char c : map.keySet()) {
            if (d[c - 'a'] == 0) {
                q.offer(c);
            }
        }
        while (q.size() > 0) {
            char cur = q.poll();
            sb.append(cur);
            for (char nei : map.get(cur)) {
                if (d[nei - 'a'] == 1) {
                    q.offer(nei);
                }
                d[nei - 'a']--;
            }
        }
        return sb.length() == map.size() ? sb.toString() : "";
    }
    // time: words.length() * word.length() space: size(unique chars)
}
