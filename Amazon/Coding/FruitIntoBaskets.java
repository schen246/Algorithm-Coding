package Amazon.Coding;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    // longest subarray with at most 2 distinct numbers
    public int totalFruit(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0, res = 0;
        while (j < nums.length) {
            int nj = nums[j];
            map.put(nj, map.getOrDefault(nj, 0) + 1);
            while (map.size() > 2) {
                int ni = nums[i];
                if (map.get(ni) == 1) {
                    map.remove(ni);
                } else {
                    map.put(ni, map.get(ni) - 1);
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}
