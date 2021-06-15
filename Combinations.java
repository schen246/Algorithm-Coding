import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(n, k, 1, cur, res);
        return res;
    }

    private void dfs(int n, int k, int index, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));// O(k) not consider?
            return;
        }
        for (int i = index; i <= n; i++) {
            cur.add(i);
            dfs(n, k, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
    // time: O(C<n,k>) space: O(k)
}
