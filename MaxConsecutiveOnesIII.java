public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        int i = 0, j = 0, cnt = 0, res = 0;
        while (j < nums.length) {
            if (nums[j] == 0) {
                cnt++;
            }
            while (cnt > k) {
                if (nums[i] == 0) {
                    cnt--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
    // M1: BF O(n*n*n) time O(1) space
    // M2: DP O(n) time O(n) space
    // M3: Sliding Window O(n) time O(1) space
}
