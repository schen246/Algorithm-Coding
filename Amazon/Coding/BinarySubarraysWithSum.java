package Amazon.Coding;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarraysWithSum {
    // M1: map + prefix sum - time: O(n) space: O(n)
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    // M2: sliding window two pass - time: O(n) space: O(1)
    // res = helper(nums, k) - helper(nums, k - 1)
    public int numSubarraysWithSum2(int[] nums, int goal) {
        return helper(nums, goal) - helper(nums, goal - 1);
    }
    
    private int helper(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        int i = 0, j = 0, sum = 0, res = 0;
        while (j < nums.length) {
            sum += nums[j];
            while (sum > k) {
                sum -= nums[i];
                i++;
            }
            res += (j - i + 1);
            j++;
        }
        return res;
    }

    // M3: three pointers one pass - time: O(n) space: O(1)
}
