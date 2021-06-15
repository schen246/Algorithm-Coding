import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int i = 0, cnt = map.size();
        for (int j = 0; j < s.length(); j++) {
            char c1 = s.charAt(j);
            // add right
            Integer n1 = map.get(c1);
            if (n1 != null) {
                if (n1 == 1) {
                    cnt--;
                }
                map.put(c1, n1 - 1);
            }
            // add res
            if (cnt == 0) {
                res.add(i);
            }
            // remove left
            if (j - i + 1 == p.length()) {
                char c2 = s.charAt(i);
                Integer n2 = map.get(c2);
                if (n2 != null) {
                    if (n2 == 0) {
                        cnt++;
                    }
                    map.put(c2, n2 + 1);
                }
                i++;
            }
        }
        return res;
    }
    // time: O(n) space: O(k)
}
