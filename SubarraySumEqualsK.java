import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    // map - time: O(n) space: O(n)
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();// <sum, cnt>
        map.put(0, 1);
        int sum = 0, res = 0;
        for (int num : nums) {
            sum += num;
            res += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
