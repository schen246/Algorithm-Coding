public class SubarrayProductLessThanK {
    // assumption: product within int range
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        int i = 0, j = 0, cnt = 0, res = 1;
        while (j < nums.length) {
            res *= nums[j];
            while (i <= j && res >= k) {
                res /= nums[i];
                i++;
            }
            cnt += (j - i + 1);// end with nums[j]
            j++;
        }
        return cnt;
    }
    // two pointers - time O(n) space O(1)
}
