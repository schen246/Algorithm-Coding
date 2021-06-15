import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfProvincesFriendCircle {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, res = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, isConnected, visited);// bfs(i, isConnected, visited)
                res++;
            }
        }
        return res;
    }

    private void dfs(int i, int[][] M, boolean[] visited) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1) {
                dfs(j, M, visited);
            }
        }
    }
    // dfs time: O(m * n) space: O(m * n)

    private void bfs(int i, int[][] M, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(i);
        visited[i] = true;
        while (q.size() > 0) {
            int cur = q.poll();
            for (int j = 0; j < M.length; j++) {
                if (M[cur][j] == 1 && !visited[j]) {
                    q.offer(j);
                    visited[j] = true;
                }
            }
        }
    }
    // bfs time: O(m * n) space: O(m * n)
}
