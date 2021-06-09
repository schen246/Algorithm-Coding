public class LongestIncreasingSubsequence {
    // M1: dp - time: O(n^2) space: O(n)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // M2: dp array with binary search - time: O(nlogn) space: O(n)
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            int index = search(dp, len, nums[i]);
            dp[index] = nums[i];
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    private int search(int[] nums, int len, int val) {
        // find the first index in [0, len - 1] st. nums[index] >= val, if not return len
        int left = 0, right = len - 1;
        int res = right + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == val) {
                return mid;
            }
            if (nums[mid] > val) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
