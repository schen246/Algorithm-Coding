import java.util.HashSet;
import java.util.Set;

public class NumberofGoodWaystoSplitaString {
    // M1: set + dp - time: O(n) space: O(n)
    public int numSplits(String s) {
        int n = s.length();
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        Set<Character> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (set.add(s.charAt(i))) {
                cnt++;
            }
            left[i + 1] = cnt;
        }
        set.clear();
        cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (set.add(s.charAt(i))) {
                cnt++;
            }
            right[n - i] = cnt;
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (left[i] == right[n - i]) {
                res++;
            }
        }
        return res;
    }
}
