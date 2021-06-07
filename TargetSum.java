public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int[] res = new int[1];
        dfs(nums, 0, S, res);
        return res[0];
    }
    
    private void dfs(int[] nums, int index, int sum, int[] res) {
        if (index == nums.length) {
            if (sum == 0) {
                res[0]++;
            }
            return;
        }
        // pos
        dfs(nums, index + 1, sum - nums[index], res);
        // neg
        dfs(nums, index + 1, sum + nums[index], res);
    }
}
