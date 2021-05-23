public class PathWithMaximumGold {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] res = new int[1];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, 0, i, j, visited, res);
                }
            }
        }
        return res[0];
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private void dfs(int[][] grid, int sum, int i, int j, boolean[][] visited, int[] res) {
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        sum += grid[i][j];
        res[0] = Math.max(res[0], sum);
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 0) {
                dfs(grid, sum, x, y, visited, res);
            }
        }
        sum -= grid[i][j];
        visited[i][j] = false;
    }
}
