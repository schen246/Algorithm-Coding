import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(k, n, 1, cur, res);
        return res;
    }

    private void dfs(int k, int n, int index, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            if (n == 0) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        for (int i = index; i <= 9; i++) {
            if (i > n) return;
            cur.add(i);
            dfs(k, n - i, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
    // time: O(9!) space: O(9)
}
