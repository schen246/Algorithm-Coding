import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    // prefix sum
    // (sum2 - sum1) % k = 0
    // sum2 % k = sum1 % k
    // map<sum, index>
    // time: O(n) space: O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) >= 2) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}
