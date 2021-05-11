public class PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> qPac = new ArrayDeque<>();
        Queue<int[]> qAtl = new ArrayDeque<>();
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    qPac.offer(new int[]{i, j});
                    pac[i][j] = true;
                }
                if (i == m - 1 || j == n - 1) {
                    qAtl.offer(new int[]{i, j});
                    atl[i][j] = true;
                }
            }
        }
        bfs(matrix, qPac, pac);
        bfs(matrix, qAtl, atl);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void bfs(int[][] matrix, Queue<int[]> q, boolean[][] visited) {
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visited[x][y]) {
                        if (matrix[x][y] >= matrix[cur[0]][cur[1]]) {
                            q.offer(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    }
                }
            }
        }
    }
    // time: O(m * n) space: O(m * n)
}
