import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeastNumberOfUniqueIntegersAfterKRemovals {
    // map + sort - time: O(nlogn) space: O(n)
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int res = map.size();
        int[] cur = new int[res];
        int i = 0;
        for (int val : map.values()) {
            cur[i++] = val;
        }
        Arrays.sort(cur);
        for (int cnt : cur) {
            if (cnt > k) {
                return res;
            }
            k -= cnt;
            res--;
        }
        return res;
    }
}
