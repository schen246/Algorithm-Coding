public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0, res = 0;
        while (j < s.length()) {
            char c1 = s.charAt(j);
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            while (map.size() > 2) {
                char c2 = s.charAt(i);
                Integer n2 = map.get(c2);
                if (n2 != null) {
                    if (n2 == 1) {
                        map.remove(c2);
                    } else {
                        map.put(c2, n2 - 1);
                    }
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
    // time: O(n) space: O(n)
}
