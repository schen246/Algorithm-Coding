public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(nums, 0, cur, res);
        return res;
    }

    private void dfs(int[] num, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        // add
        cur.add(nums[index]);
        dfs(nums, index + 1, cur, res);
        cur.remove(cur.size() - 1);
        // not add
        dfs(nums, index + 1, cur, res);
    }
    // time: O(2^n) space: O(n)
}
