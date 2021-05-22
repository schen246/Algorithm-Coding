import java.util.ArrayList;
import java.util.HashMap;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length + 1 != n) {
            return false;
        }
        // build graph
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        if (!dfs(0, -1, map, visited)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int num, int pre, Map<Integer, List<Integer>> map, boolean[] visited) {
        if (visited[num]) {
            return false;
        }
        visited[num] = true;
        for (int nei : map.get(num)) {
            if (nei != pre) {
                if (!dfs(nei, num, map, visited)) {
                    return false;
                }
            }
        }
        return true;
    }
    // method: dfs time: O(n) space: O(n)
}
