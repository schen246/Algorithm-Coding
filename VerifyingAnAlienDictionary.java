import java.util.HashMap;
import java.util.Map;

public class VerifyingAnAlienDictionary {
    // hashmap - time: O(words.length * word.length) space: O(26)
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {// O(words.length)
            String cur = words[i];
            String pre = words[i - 1];
            if (!isValid(cur, pre, map)) {// O(word.length)
                return false;
            }
        }
        return true;
    }
    
    private boolean isValid(String cur, String pre, Map<Character, Integer> map) {
        int len = Math.min(cur.length(), pre.length());
        int i = 0;
        while (i < len && cur.charAt(i) == pre.charAt(i)) {
            i++;
        }
        if (i == len) {
            return pre.length() <= cur.length();
        }
        return map.get(pre.charAt(i)) < map.get(cur.charAt(i));
    }
}
