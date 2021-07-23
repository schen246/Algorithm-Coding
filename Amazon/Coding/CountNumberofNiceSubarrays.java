package Amazon.Coding;

import java.util.HashMap;
import java.util.Map;

public class CountNumberofNiceSubarrays {
    // M1: res = atMost(nums, k) - atMost(nums, k - 1)
    // time: O(n) space: O(1)
    public int numberOfSubarrays(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }

    private int helper(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        int i = 0, j = 0, cnt = 0, res = 0;
        while (j < nums.length) {
            cnt += (nums[j] & 1);
            while (cnt > k) {
                cnt -= (nums[i] & 1);
                i++;
            }
            res += (j - i + 1);
            j++;
        }
        return res;
    }

    // M2: map - nums of subarrays with sum == k (convert to 0-1 array)
    // time: O(n) space: O(n)
    public int numberOfSubarrays2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] & 1);
            res += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    // M3: three pointers - nums of subarrays with sum == k (convert to 0-1 array)
    public int numberOfSubarrays3(int[] nums, int k) {
        int i = 0, j = 0, sum = 0, cnt = 0, res = 0;
        while (j < nums.length) {
            if ((nums[j] & 1) == 1) {
                sum++;
                cnt = 0;
            }
            while (sum == k) {
                cnt++;
                sum -= (nums[i] & 1);
                i++;
            }
            res += cnt;
            j++;
        }
        return res;
    }
}
