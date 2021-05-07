public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int i = 0, j = 0, cnt = map.size(), start = 0, len = Integer.MAX_VALUE;
        while (j < s.length()) {
            char c1 = s.charAt(j);
            Integer n1 = map.get(c1);
            if (n1 != null) {
                if (n1 == 1) {
                    cnt--;
                }
                map.put(c1, n1 - 1);
            }
            while (cnt == 0) {
                if (j - i + 1 < len) {
                    len = j - i + 1;
                    start = i;
                }
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
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
