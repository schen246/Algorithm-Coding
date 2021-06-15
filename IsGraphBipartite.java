import java.util.ArrayDeque;
import java.util.Queue;

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!bfs(i, graph, color)) {// dfs(i, 1, graph, color)
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfs(int i, int[][] graph, int[] color) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(i);
        color[i] = 1;
        while (q.size() > 0) {
            int cur = q.poll();
            int curColor = color[cur];
            for (int nei : graph[cur]) {
                if (color[nei] == curColor) {
                    return false;
                } else if (color[nei] == 0) {
                    q.offer(nei);
                    color[nei] = -curColor;
                }
            }
        }
        return true;
    }
    // bfs time: O(V + E) space: O(V)

    private boolean dfs(int i, int preColor, int[][] graph, int[] color) {
        if (color[i] != 0) {
            if (color[i] == preColor) {
                return false;
            }
            return true;
        }
        color[i] = -preColor;
        for (int nei : graph[i]) {
            if (!dfs(nei, color[i], graph, color)) {
                return false;
            }
        }
        return true;
    }
    // dfs time: O(V + E) space: O(V)
}
