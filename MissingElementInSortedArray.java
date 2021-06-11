public class MissingElementInSortedArray {
    // binary search - time: O(logn) space: O(1)
    public int missingElement(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1, res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] - nums[0] - mid < k) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[res] + (k - (nums[res] - nums[0] - res));
    }
}
