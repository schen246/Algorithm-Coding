public class LongestRepeatingSubstring {
    // M1: set - time: O(n^3) space: O(n^2)
    public int longestRepeatingSubstring(String S) {
        Set<String> set = new HashSet<>();
        int n = S.length(), res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String str = S.substring(i, j);
                if (!set.add(str)) {
                    res = Math.max(res, j - i);
                } 
            }
        }
        return res;
    }
}
