package Amazon.Coding;

import java.util.HashMap;
import java.util.Map;

public class ReplacetheSubstringforBalancedString {
    public int balancedString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('Q', 0);
        map.put('W', 0);
        map.put('E', 0);
        map.put('R', 0);
        for (char c : s.toCharArray()) {
            map.put(c, map.get(c) + 1);
        }
        int i = 0, j = 0, k = s.length() / 4, res = s.length();
        while (j < s.length()) {
            char cj = s.charAt(j);
            map.put(cj, map.get(cj) - 1);
            while (i < s.length() && map.get('Q') <= k && map.get('W') <= k && map.get('E') <= k && map.get('R') <= k) {
                res = Math.min(res, j - i + 1);
                char ci = s.charAt(i);
                map.put(ci, map.get(ci) + 1);
                i++;
            }
            j++;
        }
        return res;
    }
}
