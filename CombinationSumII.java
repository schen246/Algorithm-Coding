import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, cur, res);
        return res;
    }
    
    private void dfs(int[] candidates, int target, int index, List<Integer> cur, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < candidates.length; i++) {
            if (set.add(candidates[i])) {
                if (candidates[i] > target) {
                    return;
                }
                cur.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
    // time: O(n!) space: O(n)
}
