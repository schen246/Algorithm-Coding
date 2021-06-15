import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        List<Integer> cur = new ArrayList<>();
        dfs(candidates, target, 0, cur, res);
        return res;
    }

    private void dfs(int[] candidates, int target, int index, List<Integer> cur, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) return;
            cur.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
    // time: O(candidates.length^(target/min(candidates))) space: O(target/min(candidates))
}
