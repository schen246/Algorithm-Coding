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

    // M2: binary search - 二分答案相关
    // time: O(logn * n)
    public int longestRepeatingSubstring2(String s) {
        int left = 0, right = s.length(), res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (findRepeat(s, mid)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
    
    private boolean findRepeat(String s, int len) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - len; i++) {
            String str = s.substring(i, i + len);
            if (!set.add(str)) {
                return true;
            }
        }
        return false;
    }
}
