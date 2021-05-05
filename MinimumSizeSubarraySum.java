public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, subSum = 0, res = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            subSum += nums[j];
            while (subSum >= s) {
                res = Math.min(res, j - i + 1);
                subSum -= nums[i];
                i++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    // time: O(n) space: O(1)
}
