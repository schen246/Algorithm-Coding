public class NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        if (grid[i][j] == 1) {
            return true;
        }
        grid[i][j] = 1;
        boolean res = true;
        for (int[] dir : DIRS) {
            res &= dfs(grid, i + dir[0], j + dir[1]);
        }
        return res;
    }
    // time: O(m * n) space: O(m * n)
}
