package Amazon.Coding;

public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int i = 0, j = 0, cnt = 0, res = 0;
        while (j < nums.length) {
            cnt += nums[j] == 0 ? 1 : 0;
            while (cnt > k) {
                cnt -= nums[i] == 0 ? 1 : 0;
                i++;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}
