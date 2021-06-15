import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (set.add(c)) {
                res = Math.max(res, set.size());
                continue;
            }
            while (s.charAt(i) != c) {
                set.remove(s.charAt(i));
                i++;
            }
            i++;
        }
        return res;
    }
    // time: O(n) space: O(n)
}
