import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    // find longest subarray with at most 2 distinct numbers
    // sliding window - time: O(n) space: O(n)
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0, res = 0;
        while (j < tree.length) {
            int n1 = tree[j];
            map.put(n1, map.getOrDefault(n1, 0) + 1);
            while (map.size() > 2) {
                int n2 = tree[i];
                if (map.get(n2) == 1) {
                    map.remove(n2);
                } else {
                    map.put(n2, map.get(n2) - 1);
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}
