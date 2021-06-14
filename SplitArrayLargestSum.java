public class SplitArrayLargestSum {
    // binary search - time: O(logN * n) space: O(1)
    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            right += num;
        }
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canSplit(nums, m, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    
    private boolean canSplit(int[] nums, int m, int target) {
        int sum = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                return false;
            }
            sum += nums[i];
            if (sum > target) {
                cnt++;
                sum = nums[i];
            }
        }
        return cnt + 1 <= m;
    }
}
