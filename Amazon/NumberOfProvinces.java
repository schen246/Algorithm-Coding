public class NumberOfProvinces {
    public int findCircleNum(int[][] M) {
        int n = M.length, res = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(M, i, visited);
                res++;
            }
        }
        return res;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                dfs(M, j, visited);
            }
        }
    }
    // time: O(n^2) space: O(n)
}
