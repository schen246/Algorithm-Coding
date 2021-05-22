import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph.length == 0) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        cur.add(0);
        dfs(0, graph, cur, res);
        return res;
    }

    private void dfs(int num, int[][] graph, List<Integer> cur, List<List<Integer>> res) {
        if (num == graph.length - 1) {
            res.add(new ArrayList<>(cur));// O(n)
            return;
        }
        for (int nei : graph[num]) {
            cur.add(nei);
            dfs(nei, graph, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
    // dfs - backtracking, time: O(n! * n), space: O(n)
}
