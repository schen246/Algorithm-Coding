public class RangeSumQueryImmutable {
    int[] prefixSum;

    public RangeSumQueryImmutable(int[] nums) {// time: O(n) space: O(n)
        if (nums == null || nums.length == 0) {
            return;
        }
        prefixSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {// time: O(1)
        if (i > j || i < 0 || i > prefixSum.length - 1 || j < 0 || j > prefixSum.length - 1) {
            return -1;
        }
        return prefixSum[j + 1] - prefixSum[i];
    }
}
