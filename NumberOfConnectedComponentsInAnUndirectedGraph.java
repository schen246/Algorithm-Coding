import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        // assume n nodes [0, ..., n - 1]
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, map, visited);//bfs(i, map, visited)
                res++;
            }
        }
        return res;
    }

    private void dfs(int num, Map<Integer, List<Integer>> map, boolean[] visited) {
        if (visited[num]) {
            return;
        }
        visited[num] = true;
        for (int nei : map.get(num)) {
            dfs(nei, map, visited);
        }
    }
    // dfs time: O(n) space: O(n)

    private void bfs(int num, Map<Integer, List<Integer>> map, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(num);
        visited[num] = true;
        while (q.size() > 0) {
            int cur = q.poll();
            for (int nei : map.get(cur)) {
                if (!visited[nei]) {
                    q.offer(nei);
                    visited[nei] = true;
                }
            }
        }
    }
    // bfs time: O(n) space: O(n)
}
