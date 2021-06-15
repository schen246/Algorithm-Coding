
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences {
    //      4       6       7       7(X)
    //  6   7   7(X)
    // 7 7(X)
    // 7
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(nums, 0, cur, res);
        return res;
    }

    private void dfs(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() > 1) {
            res.add(new ArrayList<>(cur));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (cur.size() == 0 || cur.get(cur.size() - 1) <= nums[i]) {
                if (set.add(nums[i])) {
                    cur.add(nums[i]);
                    dfs(nums, i + 1, cur, res);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
    // 2^n subsequences/subsets
    // dfs - backtracking - time: O(n * 2^n) space: O(n * n) with set in each layer
}
