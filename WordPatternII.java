import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternII {
    // backtracking + early return - time: O(str.length() ^ pattern.length()) space: O(pattern.length()) call stack + O(2^str.length())
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return dfs(pattern, 0, str, 0, map, set);
    }
    
    private boolean dfs(String pattern, int index1, String str, int index2, Map<Character, String> map, Set<String> set) {
        if (index1 == pattern.length()) {
            return index2 == str.length();
        }
        char ch = pattern.charAt(index1);
        if (map.get(ch) != null) {
            if (!str.startsWith(map.get(ch), index2)) {
                return false;
            }
            return dfs(pattern, index1 + 1, str, index2 + map.get(ch).length(), map, set);
        }
        for (int i = index2; i < str.length(); i++) {
            String cur = str.substring(index2, i + 1);
            if (!set.add(cur)) {
                continue;
            }
            map.put(ch, cur);
            if (dfs(pattern, index1 + 1, str, i + 1, map, set)) {
                return true;
            }
            map.remove(ch);
            set.remove(cur);
        }
        return false;
    }
}
